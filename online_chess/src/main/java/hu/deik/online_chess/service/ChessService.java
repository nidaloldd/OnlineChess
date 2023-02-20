package hu.deik.online_chess.service;

import hu.deik.online_chess.model.*;

import java.util.List;

public interface ChessService {
    List<Position> getValidMoves(Position position);
    Table getTable();
    String getTableAsJson();
}
