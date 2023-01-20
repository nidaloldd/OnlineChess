package model.figures;

import model.*;

import java.util.ArrayList;
import java.util.List;

public class Queen extends Figure {

    public Queen(Table table, Color color, String strPos) {
        super(table,color, Position.toPosition(strPos));
    }
    @Override
    public List<Position> getValidMoves(boolean handleKingInCheck){
        List<Position> validMoves = new ArrayList<>();

        for(Direction direction : Direction.getAllDirections()){
            validMoves.addAll(getValidMovesFromOneDirection(direction));
        }

        if(handleKingInCheck){
            validMoves = handleKingInCheck(validMoves);
        }

        return validMoves;
    }
    @Override
    public String toString() {
        if(color == Color.WHITE){
            return " WQ ";
        }
        return " BQ ";
    }
}
