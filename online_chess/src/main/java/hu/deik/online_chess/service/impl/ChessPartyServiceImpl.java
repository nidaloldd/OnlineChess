package hu.deik.online_chess.service.impl;

import hu.deik.online_chess.data.ChessParty;
import hu.deik.online_chess.data.Player;
import hu.deik.online_chess.exeption.InvalidGameException;
import hu.deik.online_chess.exeption.InvalidParamException;
import hu.deik.online_chess.exeption.NotFoundException;
import hu.deik.online_chess.manager.ChessGameManager;
import hu.deik.online_chess.model.*;
import hu.deik.online_chess.service.ChessPartyService;
import hu.deik.online_chess.service.dto.MoveRequest;
import lombok.AllArgsConstructor;
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
    public ChessParty makeMove(MoveRequest request) throws NotFoundException, InvalidGameException {
        log.info("request:{}",request.getId());

        String id = request.getId();
        //id = id.substring(id.lastIndexOf(':')+2,id.length()-2);
        log.info("request:{}",id);
        if (!ChessGameManager.getInstance().getGames().containsKey(id)) {
            throw new NotFoundException("Game not found");
        }

        ChessParty game = ChessGameManager.getInstance().getGames().get(id);
        if (game.getStatus().equals(FINISHED)) {
            throw new InvalidGameException("Game is already finished");
        }

        game.getTable().makeMove(Position.toPosition(request.getFrom()),Position.toPosition(request.getTo()));

        if (game.getTable().isGameOver) {
            game.setWinner(getActivePlayer(request.getId()));
        }

        ChessGameManager.getInstance().setGame(game);
        return game;
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
        }

        ChessGameManager.getInstance().setGame(game);
        return game;
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
