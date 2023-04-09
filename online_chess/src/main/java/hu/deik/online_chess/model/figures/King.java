package hu.deik.online_chess.model.figures;

import hu.deik.online_chess.model.Color;
import hu.deik.online_chess.model.Direction;
import hu.deik.online_chess.model.Position;
import hu.deik.online_chess.model.Table;

import java.util.ArrayList;
import java.util.List;

public class King extends Figure implements ChessFigure {
    public King(Color color, String strPos) {
        super(color, Position.toPosition(strPos));
    }

    @Override
    public List<Position> getValidMoves(Table table,boolean handleKingInCheck){
        List<Position> validMoves = new ArrayList<>();

        for(Direction direction : Direction.getAllDirections()){
           validMoves.addAll(getValidMoveFromOneDirectionOnlyOneStep(table,direction));
        }
        validMoves.removeAll(table.getEnemyValidMoves(color));
        addPossibleCastle(table,validMoves);

        if(handleKingInCheck){
            validMoves = this.handleKingInCheck(table,validMoves);
        }
        return validMoves;
    }
    private void addPossibleCastle(Table table,List<Position> validMoves){
        validMoves.addAll(addPossibleCastleForColor(table,validMoves,color));
    }
    private List<Position> addPossibleCastleForColor(Table table,List<Position> validMoves, Color color){
        if(!table.getKing(color).getIfFigureNotMoved() || table.isKingInCheck(color)){return validMoves;}

        final String row;
        if(color == Color.WHITE){row = "1";}
        else {row = "8";}

        if( table.getFigureOn("F"+row)== null &&
                table.getFigureOn("G"+row)== null &&
                table.getFigureOn("H"+row)!= null &&
                table.getFigureOn("H"+row).getIfFigureNotMoved() &&
                validMoves.contains(Position.toPosition("F"+row)))
        {
            validMoves.add(Position.toPosition("G"+row));
        }
        if( table.getFigureOn("D"+row)== null &&
                table.getFigureOn("C"+row)== null &&
                table.getFigureOn("B"+row)== null &&
                table.getFigureOn("A"+row)!=null &&
                table.getFigureOn("A"+row).getIfFigureNotMoved()&&
                validMoves.contains(Position.toPosition("D"+row)))
        {
            validMoves.add(Position.toPosition("C"+row));
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
