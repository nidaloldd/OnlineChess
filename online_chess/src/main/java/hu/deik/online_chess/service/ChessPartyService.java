package hu.deik.online_chess.service;

import hu.deik.online_chess.data.ChessParty;
import hu.deik.online_chess.data.Player;
import hu.deik.online_chess.exeption.InvalidGameException;
import hu.deik.online_chess.exeption.InvalidParamException;
import hu.deik.online_chess.exeption.NotFoundException;
import hu.deik.online_chess.model.GameStatus;
import hu.deik.online_chess.model.Position;
import hu.deik.online_chess.model.Table;

import java.util.List;

public interface ChessPartyService {
    public ChessParty createGame(Player player1,GameStatus gameStatus) ;

    ChessParty connectToGame(Player player2, String gameId) throws InvalidParamException, InvalidGameException;

    ChessParty connectToRandomGame(Player player2) throws NotFoundException;

    public ChessParty makeMove(String gameID,String from,String to) throws NotFoundException, InvalidGameException ;
    List<Position> getValidMoves(String gameId,Position position);
    Player getActivePlayer(String gameId);
    Table getTable(String gameId);

}