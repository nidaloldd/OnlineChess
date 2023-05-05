package hu.deik.online_chess.model.figures;

import hu.deik.online_chess.model.Color;
import hu.deik.online_chess.model.Direction;
import hu.deik.online_chess.model.Position;
import hu.deik.online_chess.model.Table;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends Figure implements ChessFigure {

    public Pawn(Color color, String strPos) {
        super(color, Position.toPosition(strPos));
    }

    private boolean canDoEntPassantRight = false;
    private boolean canDoEntPassantLeft = false;

    public boolean isCanDoEntPassantRight() {
        return canDoEntPassantRight;
    }

    public void setCanDoEntPassantRight(boolean canDoEntPassantRight) {
        this.canDoEntPassantRight = canDoEntPassantRight;
    }

    public boolean isCanDoEntPassantLeft() {
        return canDoEntPassantLeft;
    }

    public void setCanDoEntPassantLeft(boolean canDoEntPassantLeft) {
        this.canDoEntPassantLeft = canDoEntPassantLeft;
    }
    @Override
    public List<Position> getValidMoves(Table table,boolean handleKingInCheck){
        List<Position> validMoves = new ArrayList<>();

        Direction forward = Direction.getDirectionForward(color);
        Direction forwardRight = Direction.getDirectionForwardRight(color);
        Direction forwardLeft = Direction.getDirectionForwardLeft(color);

        if(table.isPositionNotOccupied(position.stepToDirection(forward))){
            validMoves.add(position.stepToDirection(forward));

            if(table.isPositionNotOccupied(position.stepToDirection(forward)
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

        addPossibleEnPassant(validMoves);

        if(handleKingInCheck){
            validMoves = handleKingInCheck(table,validMoves);
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
        return color == Color.WHITE && Position.toString(position).charAt(1) == '2';
    }
    private void addPossibleEnPassant(List<Position> validMoves){
        Direction forwardRight = Direction.getDirectionForwardRight(color);
        Direction forwardLeft = Direction.getDirectionForwardLeft(color);

        if(canDoEntPassantLeft){
            validMoves.add(position.stepToDirection(forwardLeft));
        }
        if(canDoEntPassantRight){
            validMoves.add(position.stepToDirection(forwardRight));
        }
    }
    public void makeEnPassantFalse(){
        canDoEntPassantLeft = false;
        canDoEntPassantRight = false;
    }

    @Override
    public String figureAsString() {
        if(color == Color.WHITE){
            return " WP ";
        }
        return " BP ";
    }

}
