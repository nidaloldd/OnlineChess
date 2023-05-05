package hu.deik.online_chess.model.figures;
import hu.deik.online_chess.model.Position;
import hu.deik.online_chess.model.Table;

import java.util.List;

public interface ChessFigure {
    public List<Position> getValidMoves(Table table, boolean handleKingInCheck);

    public String figureAsString();
}
