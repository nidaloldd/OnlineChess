package Model.Figures;

import Model.*;

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
    @Override
    public List<Position> getValidMovesFromOneDirection(Direction direction){
        List<Position> validMoves = new ArrayList<>();
        Position stepPosition = position;

        stepPosition = stepPosition.stepToDirection(direction);
        Position actualPosition = stepPosition;

        Direction[] dirs = {Direction.getDirectionOf(direction.getxChange(),0),
                            Direction.getDirectionOf(0,direction.getyChange())};

        for(Direction dir : dirs){
            actualPosition = stepPosition;
            actualPosition = actualPosition.stepToDirection(dir);
            if(!actualPosition.isPositionValid()){continue;}
            if(isPositionOccupiedByEnemy(actualPosition,color)){
                validMoves.add(actualPosition);
                continue;
            }
            if(!isPositionOccupied(actualPosition)) {
                validMoves.add(actualPosition);
            }
        }
        return validMoves;
    }

    @Override
    public String toString() {
        if(color == Color.White){
            return " WN ";
        }
        return " BN ";
    }
}