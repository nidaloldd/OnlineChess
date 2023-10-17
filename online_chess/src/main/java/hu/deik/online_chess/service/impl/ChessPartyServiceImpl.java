package hu.deik.online_chess.service.impl;

import hu.deik.online_chess.data.ChessParty;
import hu.deik.online_chess.data.Player;
import hu.deik.online_chess.exeption.InvalidGameException;
import hu.deik.online_chess.exeption.InvalidParamException;
import hu.deik.online_chess.exeption.NotFoundException;
import hu.deik.online_chess.manager.ChessGameManager;
import hu.deik.online_chess.model.*;
import hu.deik.online_chess.repo.PlayerRepository;
import hu.deik.online_chess.service.ChessPartyService;
import hu.deik.online_chess.service.PlayerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static hu.deik.online_chess.model.GameStatus.*;

@Service
@AllArgsConstructor
@Slf4j
public class ChessPartyServiceImpl implements ChessPartyService {

    private ChessParty chessParty;
    private PlayerService playerService;
    private PlayerRepository playerRepository;
    @Autowired
    public ChessPartyServiceImpl(PlayerService playerService,PlayerRepository playerRepository){
        this.playerService = playerService;
        this.playerRepository = playerRepository;
    }

    @Override
    public ChessParty createGame(Player player,GameStatus gameStatus) {
        chessParty = new ChessParty();
        chessParty.setId(UUID.randomUUID().toString());
        chessParty.setWhitePlayer(player);
        chessParty.setStatus(gameStatus);
        ChessGameManager.getGameManager().setGame(chessParty);
        return chessParty;
    }
    @Override
    public ChessParty connectToGame(Player player, String gameId) throws InvalidParamException, InvalidGameException {
        log.info("connectToGame");
        log.info("getUsername:{}",player.getUsername());
        gameId = gameId.substring(gameId.lastIndexOf(':')+2,gameId.length()-2);
        log.info("gameId:{}",gameId);

        if (!ChessGameManager.getGameManager().getGames().containsKey(gameId)) {
            throw new InvalidParamException("Game with provided id doesn't exist");
        }
        ChessParty game = ChessGameManager.getGameManager().getGames().get(gameId);

        ChessGameManager.getGameManager().setGame(game);
        return game;
    }
    @Override
    public ChessParty connectToRandomGame(Player player) throws NotFoundException {
        var newGame = ChessGameManager.getGameManager().getGames().values().stream()
                .filter(it -> it.getStatus().equals(NEW)).findAny();

        if(newGame.isEmpty()){
            log.info("createGame");
           return createGame(player,GameStatus.NEW);
        }
        else {
            var game = newGame.get();

            game.setBlackPlayer(player);

            game.setStatus(IN_PROGRESS);
            ChessGameManager.getGameManager().setGame(game);

            log.info("connectToRandomGame");
            return game;
        }
    }


    @Override
    public ChessParty makeMove(String gameID,String from,String to) throws NotFoundException, InvalidGameException {

        if (!ChessGameManager.getGameManager().getGames().containsKey(gameID)) {
            throw new NotFoundException("Game not found");
        }

        ChessParty game = ChessGameManager.getGameManager().getGames().get(gameID);
        if (game.getStatus().equals(FINISHED)) {
            throw new InvalidGameException("Game is already finished");
        }

        game.getTable().makeMove(from,to);

        if (game.getTable().isGameOver()) {
            game.setWinner(getActivePlayer(gameID));

            handleScore(game.getWhitePlayer(),game.getBlackPlayer(), game.getWinner());
        }


        ChessGameManager.getGameManager().setGame(game);
        return game;
    }

    private void handleScore(Player white,Player black, Player winner){
        if(white == null || black == null || winner==null){return;}

        if(white.getUsername().equals(winner.getUsername())){

            double ratingChange = playerService.absoluteRatingChange(white.getScore(),black.getScore(),GameResult.WIN);
            white.setScore(white.getScore()+ratingChange);
            black.setScore(black.getScore()-ratingChange);

        }
        else if(black.getUsername().equals(winner.getUsername())){
            double ratingChange = playerService.absoluteRatingChange(white.getScore(),black.getScore(),GameResult.LOSE);
            white.setScore(white.getScore()-ratingChange);
            black.setScore(black.getScore()+ratingChange);
        }

        playerRepository.save(white);
        playerRepository.save(black);
    }

    @Override
    public List<Position> getValidMoves(String gameId,Position position) {
        var table = getTable(gameId);
        return table.getFigureOn(position).getValidMoves(table);
    }
    @Override
    public Player getActivePlayer(String gameId){
        if(getTable(gameId).getActivePlayerColor() == Color.WHITE){
            return chessParty.getWhitePlayer();
        }
        else {
            return chessParty.getBlackPlayer();
        }
    }
    @Override
    public Table getTable(String gameId){
        ChessParty game = ChessGameManager.getGameManager().getGames().get(gameId);
        return game.getTable();
    }

}
