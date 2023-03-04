package hu.deik.online_chess.service.impl;

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
    public ChessParty createGame(Player player1) {
        chessParty = new ChessParty();
        chessParty.setId(UUID.randomUUID().toString());
        chessParty.setWhitePlayer(player1);
        chessParty.setStatus(NEW);
        ChessGameManager.getInstance().setGame(chessParty);
        return chessParty;
    }
    @Override
    public ChessParty connectToGame(Player player2, String gameId) throws InvalidParamException, InvalidGameException {
        if (!ChessGameManager.getInstance().getGames().containsKey(gameId)) {
            throw new InvalidParamException("Game with provided id doesn't exist");
        }
        ChessParty game = ChessGameManager.getInstance().getGames().get(gameId);

        if (game.getBlackPlayer() != null) {
            throw new InvalidGameException("Game is not valid anymore");
        }

        game.setBlackPlayer(player2);
        game.setStatus(IN_PROGRESS);

        ChessGameManager.getInstance().setGame(game);
        return game;
    }
    @Override
    public ChessParty connectToRandomGame(Player player2) throws NotFoundException {
        ChessParty game = ChessGameManager.getInstance().getGames().values().stream()
                .filter(it -> it.getStatus().equals(NEW))
                .findFirst().orElseThrow(() -> new NotFoundException("Game not found"));
        game.setBlackPlayer(player2);
        game.setStatus(IN_PROGRESS);
        ChessGameManager.getInstance().setGame(game);
        return game;
    }
    @Override
    public ChessParty makeMove(MoveRequest request) throws NotFoundException, InvalidGameException {
        if (!ChessGameManager.getInstance().getGames().containsKey(request.getId())) {
            throw new NotFoundException("Game not found");
        }

        ChessParty game = ChessGameManager.getInstance().getGames().get(request.getId());
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
    public List<Position> getValidMoves(String gameId,Position position) {
        return getTable(gameId).getFigureOn(position).getValidMoves();
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
