package Model.Figures;
import Model.*;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends Figure {

    public Pawn(Table table, Color color, String strPos) {
        super(table,color, Position.toPosition(strPos));
    }
    @Override
    public List<Position> getValidMoves(boolean handleKingInCheck){
        List<Position> validMoves = new ArrayList<>();

        Direction forward = Direction.getDirectionForward(color);
        Direction forwardRight = Direction.getDirectionForwardRight(color);
        Direction forwardLeft = Direction.getDirectionForwardLeft(color);

        if(!isPositionOccupied(position.stepToDirection(forward))){
            validMoves.add(position.stepToDirection(forward));

            if(!isPositionOccupied(position.stepToDirection(forward)
                    .stepToDirection(forward)) &&
                    isStartingRow()){
                validMoves.add(position.stepToDirection(forward).stepToDirection(forward));
            }
        }

        if(isPositionOccupiedByEnemy(position.stepToDirection(forwardRight),color)){
            validMoves.add(position.stepToDirection(forwardRight));
        }
        if(isPositionOccupiedByEnemy(position.stepToDirection(forwardLeft),color)){
            validMoves.add(position.stepToDirection(forwardLeft));
        }

        if(handleKingInCheck){
            validMoves =handleKingInCheck(validMoves);
        }

        return validMoves;
    }
    public List<Position> getPossibleAttackMoves(){

        List<Position> moves = new ArrayList<>();
        Direction forwardRight = Direction.getDirectionForwardRight(color);
        Direction forwardLeft = Direction.getDirectionForwardLeft(color);

        moves.add(position.stepToDirection(forwardRight));
        moves.add(position.stepToDirection(forwardLeft));

        return moves;
    }
    private boolean isStartingRow(){
        if(color == Color.Black && Position.toString(position).charAt(1)=='7'){
            return true;
        }
        if(color == Color.White && Position.toString(position).charAt(1)=='2'){
            return true;
        }
        return false;
    }
    @Override
    public String toString() {
        if(color == Color.White){
            return " WP ";
        }
        return " BP ";
    }

}
