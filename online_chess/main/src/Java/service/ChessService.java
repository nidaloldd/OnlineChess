package Java.service;

import Java.model.Position;
import Java.model.Table;

import java.util.List;

public interface ChessService {
    List<Position> getValidMoves(Position position);
    Table getTable();
}
