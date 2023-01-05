package Model.Figures;

import Model.*;

import java.util.ArrayList;
import java.util.List;

public class King extends Figure {
    public boolean inCheck = false;
    public King(Table table, Color color, String strPos) {
        super(table,color, Position.toPosition(strPos));
    }

    @Override
    public List<Position> getValidMoves(boolean handleKingInCheck){
        List<Position> validMoves = new ArrayList<>();

        for(Direction direction : Direction.getAllDirections()){
           validMoves.addAll(getValidMovesFromOneDirection(direction,"onlyOneStep"));
        }

        validMoves.removeAll(table.getEnemyValidMoves(color));

        if(handleKingInCheck){
            validMoves =handleKingInCheck(validMoves);
        }

        return validMoves;
    }
    @Override
    public String toString() {
        if(color == Color.White){
            return " WK ";
        }
        return " BK ";
    }
}
