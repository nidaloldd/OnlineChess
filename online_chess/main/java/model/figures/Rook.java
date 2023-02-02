package model.figures;

import model.*;

import java.util.ArrayList;
import java.util.List;

public class Rook extends Figure {

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