package hu.deik.online_chess.model.figures;
import hu.deik.online_chess.model.Position;
import hu.deik.online_chess.model.Table;

import java.util.List;

public interface ChessFigure {
    public List<Position> getValidMoves(Table table);
    public List<Position> getAllPossibleMoves(Table table);
    public String getFigureAsString();
}
