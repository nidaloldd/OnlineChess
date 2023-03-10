package hu.deik.online_chess.service;

import hu.deik.online_chess.exeption.InvalidGameException;
import hu.deik.online_chess.exeption.InvalidParamException;
import hu.deik.online_chess.exeption.NotFoundException;
import hu.deik.online_chess.model.*;
import hu.deik.online_chess.service.dto.MoveRequest;

import java.util.List;

public interface ChessPartyService {
    ChessParty createGame(Player player1);

    ChessParty connectToGame(Player player2, String gameId) throws InvalidParamException, InvalidGameException;

    ChessParty connectToRandomGame(Player player2) throws NotFoundException;

    ChessParty makeMove(MoveRequest request) throws NotFoundException, InvalidGameException;
    List<Position> getValidMoves(String gameId,Position position);
    Player getActivePlayer(String gameId);
    Table getTable(String gameId);

}