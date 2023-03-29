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
import hu.deik.online_chess.service.dto.MoveRequest;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
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
    public ChessParty createGame(Player player1,GameStatus gameStatus) {
        chessParty = new ChessParty();
        chessParty.setId(UUID.randomUUID().toString());
        chessParty.setWhitePlayer(player1);
        chessParty.setStatus(gameStatus);
        ChessGameManager.getInstance().setGame(chessParty);
        return chessParty;
    }
    @Override
    public ChessParty connectToGame(Player player2, String gameId) throws InvalidParamException, InvalidGameException {
        log.info("connectToGame");
        log.info("getUsername:{}",player2.getUsername());
        log.info("gameId:{}",gameId);
        gameId = gameId.substring(gameId.lastIndexOf(':')+2,gameId.length()-2);
        log.info("gameId:{}",gameId);

        if (!ChessGameManager.getInstance().getGames().containsKey(gameId)) {
            throw new InvalidParamException("Game with provided id doesn't exist");
        }
        ChessParty game = ChessGameManager.getInstance().getGames().get(gameId);

        ChessGameManager.getInstance().setGame(game);
        return game;
    }
    @Override
    public ChessParty connectToRandomGame(Player player2) throws NotFoundException {
        var newGame = ChessGameManager.getInstance().getGames().values().stream()
                .filter(it -> it.getStatus().equals(NEW)).findAny();

        if(newGame.isEmpty()){
            log.info("createGame");
            var size = ChessGameManager.getInstance().getGames().size();
            log.info("ChessGameManager size :{}", size);
           return createGame(player2,GameStatus.NEW);
        }
        else {
            var game = newGame.get();

            game.setBlackPlayer(player2);

            game.setStatus(IN_PROGRESS);
            ChessGameManager.getInstance().setGame(game);

            log.info("connectToRandomGame");
            var size = ChessGameManager.getInstance().getGames().size();
            log.info("ChessGameManager size :{}", size);
            return game;
        }
    }


    @Override
    public ChessParty makeMove(String gameID,String from,String to) throws NotFoundException, InvalidGameException {

        if (!ChessGameManager.getInstance().getGames().containsKey(gameID)) {
            throw new NotFoundException("Game not found");
        }

        ChessParty game = ChessGameManager.getInstance().getGames().get(gameID);
        if (game.getStatus().equals(FINISHED)) {
            throw new InvalidGameException("Game is already finished");
        }

        game.getTable().makeMove(Position.toPosition(from),Position.toPosition(to));

        if (game.getTable().isGameOver) {
            game.setWinner(getActivePlayer(gameID));

            handleScore(game.getWhitePlayer(),game.getBlackPlayer(), game.getWinner());
        }


        ChessGameManager.getInstance().setGame(game);
        return game;
    }

    private void handleScore(Player white,Player black, Player winner){
        if(white == null || black == null || winner==null){return;}

        System.out.println("white.getScore() "+white.getScore());
        System.out.println("black.getScore() "+black.getScore());
        if(white.getUsername().equals(winner.getUsername())){

            double ratingChange = playerService.absoluteRatingChange(white.getScore(),black.getScore(),GameResult.WIN);
            System.out.println("ratingChange "+ratingChange);
            white.setScore(white.getScore()+ratingChange);
            black.setScore(black.getScore()-ratingChange);

        }
        else if(black.getUsername().equals(winner.getUsername())){
            double ratingChange = playerService.absoluteRatingChange(white.getScore(),black.getScore(),GameResult.LOSE);
            System.out.println("ratingChange "+ratingChange);
            white.setScore(white.getScore()-ratingChange);
            black.setScore(black.getScore()+ratingChange);
        }
        System.out.println("white.getScore() "+white.getScore());
        System.out.println("black.getScore() "+black.getScore());

        playerRepository.save(white);
        playerRepository.save(black);
        //playerRepository.updatePlayerScore(white.getId(), white.getScore());
        //playerRepository.updatePlayerScore(black.getId(), black.getScore());
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
        ChessParty game = ChessGameManager.getInstance().getGames().get(gameId);
        return chessParty.getTable();
    }

}
