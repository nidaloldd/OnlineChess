package Java.model.figures;

import Java.model.Color;
import Java.model.Direction;
import Java.model.Position;
import Java.model.Table;


import java.util.ArrayList;
import java.util.List;

public class Knight extends Figure {

    public Knight(Table table, Color color, String strPos) {
        super(table,color, Position.toPosition(strPos));
    }
    @Override
    public List<Position> getValidMoves(boolean handleKingInCheck){
        List<Position> validMoves = new ArrayList<>();

        for(Direction diagonalDir : Direction.getDiagonalDirections()){
            validMoves.addAll(getValidMovesFromOneDirection(diagonalDir));
        }

        if(handleKingInCheck){
            validMoves = handleKingInCheck(validMoves);
        }

        return validMoves;
    }

    public List<Position> getValidMovesFromOneDirection(Direction direction){
        List<Position> validMoves = new ArrayList<>();
        Position stepPosition = position;

        stepPosition = stepPosition.stepToDirection(direction);
        Position actualPosition;

        Direction[] dirs = {Direction.getDirectionOf(direction.getXChange(),0),
                            Direction.getDirectionOf(0,direction.getYChange())};

        for(Direction dir : dirs){
            actualPosition = stepPosition;
            actualPosition = actualPosition.stepToDirection(dir);
            if(actualPosition.isPositionNotValid()){continue;}
            if(table.isPositionOccupiedByEnemy(actualPosition,color)){
                validMoves.add(actualPosition);
                continue;
            }
            if(table.isPositionNotOccupied(actualPosition)) {
                validMoves.add(actualPosition);
            }
        }
        return validMoves;
    }

    @Override
    public String toString() {
        if(color == Color.WHITE){
            return " WN ";
        }
        return " BN ";
    }
}