package hu.deik.online_chess.model.figures;

import hu.deik.online_chess.model.Color;
import hu.deik.online_chess.model.Direction;
import hu.deik.online_chess.model.Position;
import hu.deik.online_chess.model.Table;

import java.util.ArrayList;
import java.util.List;

public class Rook extends Figure implements ChessFigure {

    public Rook(Table table, Color color, String strPos) {
        super(table,color, Position.toPosition(strPos));
    }
    @Override
    public List<Position> getValidMoves(boolean handleKingInCheck){
        List<Position> validMoves = new ArrayList<>();

        for(Direction direction : Direction.getStraightDirections()){
            validMoves.addAll(getValidMovesFromOneDirectionManyStep(direction));
        }

        if(handleKingInCheck){
            validMoves = handleKingInCheck(validMoves);
        }
        return validMoves;
    }
    @Override
    public String toString() {
        if(color == Color.WHITE){
            return " WR ";
        }
        return " BR ";
    }
}
