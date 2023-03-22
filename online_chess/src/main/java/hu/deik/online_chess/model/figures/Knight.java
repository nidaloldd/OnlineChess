package hu.deik.online_chess.model.figures;

import hu.deik.online_chess.model.Color;
import hu.deik.online_chess.model.Direction;
import hu.deik.online_chess.model.Position;
import hu.deik.online_chess.model.Table;


import java.util.ArrayList;
import java.util.List;

public class Knight extends Figure implements ChessFigure {

    public Knight(Table table, Color color, String strPos) {
        super(color, Position.toPosition(strPos));
    }
    @Override
    public List<Position> getValidMoves(Table table, boolean handleKingInCheck){
        List<Position> validMoves = new ArrayList<>();

        for(Direction diagonalDir : Direction.getDiagonalDirections()){
            validMoves.addAll(getValidMovesFromOneDirection(table,diagonalDir));
        }

        if(handleKingInCheck){
            validMoves = handleKingInCheck(table,validMoves);
        }

        return validMoves;
    }

    public List<Position> getValidMovesFromOneDirection(Table table,Direction direction){
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