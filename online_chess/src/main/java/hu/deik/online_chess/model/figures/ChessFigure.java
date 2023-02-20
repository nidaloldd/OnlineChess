package hu.deik.online_chess.model.figures;
import hu.deik.online_chess.model.Position;

import java.util.List;

public interface ChessFigure {
    public List<Position> getValidMoves(boolean handleKingInCheck);
    @Override
    public String toString();
}
