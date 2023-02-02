package model.figures;

import model.*;

import java.util.ArrayList;
import java.util.List;

public class King extends Figure {
    public King(Table table, Color color, String strPos) {
        super(table,color, Position.toPosition(strPos));
    }

    @Override
    public List<Position> getValidMoves(boolean handleKingInCheck){
        List<Position> validMoves = new ArrayList<>();

        for(Direction direction : Direction.getAllDirections()){
           validMoves.addAll(getValidMovesFromOneDirectionOnlyOneStep(direction));
        }

        validMoves.removeAll(table.getEnemyValidMoves(color));
        addPossibleCastle(validMoves);

        if(handleKingInCheck){
            validMoves =handleKingInCheck(validMoves);
        }
        return validMoves;
    }
    private void addPossibleCastle(List<Position> validMoves){
        validMoves.addAll(addPossibleCastleForColor(validMoves,color));
    }
    private List<Position> addPossibleCastleForColor(List<Position> validMoves, Color color){
        final String row;
        if(color == Color.WHITE){row = "1";}
        else {row = "8";}

        if(table.getKing(color).getIfFigureNotMoved()){
            if( table.getFigureOn("F"+row)== null &&
                    table.getFigureOn("G"+row)== null &&
                    table.getFigureOn("H"+row).getIfFigureNotMoved())

            {
                validMoves.add(Position.toPosition("G"+row));
            }
            if( table.getFigureOn("D"+row)== null &&
                    table.getFigureOn("C"+row)== null &&
                    table.getFigureOn("B"+row)== null &&
                    table.getFigureOn("A"+row).getIfFigureNotMoved()
            )

            {
                validMoves.add(Position.toPosition("C"+row));
            }
        }
        return validMoves;
    }
    @Override
    public String toString() {
        if(color == Color.WHITE){
            return " WK ";
        }
        return " BK ";
    }
}
