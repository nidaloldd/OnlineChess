package Java.model.figures;

import Java.model.Color;
import Java.model.Direction;
import Java.model.Position;
import Java.model.Table;

import java.util.ArrayList;
import java.util.List;

public class Bishop extends Figure {

    public Bishop(Table table, Color color, String strPos) {
        super(table,color, Position.toPosition(strPos));
    }
    @Override
    public List<Position> getValidMoves(boolean handleKingInCheck){
        List<Position> validMoves = new ArrayList<>();

        for(Direction direction : Direction.getDiagonalDirections()){
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
            return " WB ";
        }
        return " BB ";
    }
}
