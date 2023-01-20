package Model.Figures;
import Model.*;
import java.util.ArrayList;
import java.util.List;

public class Pawn extends Figure {

    public Pawn(Table table, Color color, String strPos) {
        super(table,color, Position.toPosition(strPos));
    }
    public boolean canDoEntPassantRight = false;
    public boolean canDoEntPassantLeft = false;
    @Override
    public List<Position> getValidMoves(boolean handleKingInCheck){
        List<Position> validMoves = new ArrayList<>();

        Direction forward = Direction.getDirectionForward(color);
        Direction forwardRight = Direction.getDirectionForwardRight(color);
        Direction forwardLeft = Direction.getDirectionForwardLeft(color);

        if(!table.isPositionOccupied(position.stepToDirection(forward))){
            validMoves.add(position.stepToDirection(forward));

            if(!table.isPositionOccupied(position.stepToDirection(forward)
                    .stepToDirection(forward)) &&
                    isStartingRow(position)){
                validMoves.add(position.stepToDirection(forward).stepToDirection(forward));
            }
        }

        if(table.isPositionOccupiedByEnemy(position.stepToDirection(forwardRight),color)){
            validMoves.add(position.stepToDirection(forwardRight));
        }
        if(table.isPositionOccupiedByEnemy(position.stepToDirection(forwardLeft),color)){
            validMoves.add(position.stepToDirection(forwardLeft));
        }

        validMoves = addPossibleEnPassant(validMoves);

        if(handleKingInCheck){
            validMoves = handleKingInCheck(validMoves);
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
    private boolean isStartingRow(Position position){
        if(color == Color.BLACK && Position.toString(position).charAt(1)=='7'){
            return true;
        }
        if(color == Color.WHITE && Position.toString(position).charAt(1)=='2'){
            return true;
        }
        return false;
    }
    public boolean isLastRow(Position position){
        if(color == Color.BLACK && Position.toString(position).charAt(1)=='1'){
            return true;
        }
        if(color == Color.WHITE && Position.toString(position).charAt(1)=='8'){
            return true;
        }
        return false;
    }
    private List<Position> addPossibleEnPassant(List<Position> validMoves){
        Direction forwardRight = Direction.getDirectionForwardRight(color);
        Direction forwardLeft = Direction.getDirectionForwardLeft(color);

        if(canDoEntPassantLeft){
            validMoves.add(position.stepToDirection(forwardLeft));
        }
        if(canDoEntPassantRight){
            validMoves.add(position.stepToDirection(forwardRight));
        }
        return validMoves;
    }
    public void makeEnPassantFalse(){
        canDoEntPassantLeft = false;
        canDoEntPassantRight = false;
    }

    @Override
    public String toString() {
        if(color == Color.WHITE){
            return " WP ";
        }
        return " BP ";
    }

}
