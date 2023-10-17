package hu.deik.online_chess.model.figures;

import hu.deik.online_chess.model.Color;
import hu.deik.online_chess.model.Direction;
import hu.deik.online_chess.model.Position;
import hu.deik.online_chess.model.Table;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends Figure implements ChessFigure {
    private boolean canDoEnPassantRight = false;
    private boolean canDoEnPassantLeft = false;
    private final Direction forward = Direction.getDirectionForward(color);
    private final Direction forwardLeft = Direction.getDirectionForwardLeft(color);
    private final Direction forwardRight = Direction.getDirectionForwardRight(color);

    public Pawn(Color color, String strPos) {
        super(color, Position.toPosition(strPos));
    }

    public void setCanDoEnPassantRight(boolean canDoEnPassantRight) {
        this.canDoEnPassantRight = canDoEnPassantRight;
    }

    public void setCanDoEnPassantLeft(boolean canDoEnPassantLeft) {
        this.canDoEnPassantLeft = canDoEnPassantLeft;
    }

    @Override
    public List<Position> getAllPossibleMoves(Table table) {
        List<Position> validMoves = new ArrayList<>();

        addForwardMovement(table, validMoves);
        addAttackMovement(table, validMoves);
        addPossibleEnPassant(validMoves);

        return validMoves;
    }

    private void addForwardMovement(Table table, List<Position> validMoves) {
        if (canMoveForward(table)) {
            validMoves.add(position.stepToDirection(forward));

            if (canMoveTwoSquareForward(table)) {
                validMoves.add(position.stepToDirection(forward).stepToDirection(forward));
            }
        }
    }

    private boolean canMoveForward(Table table) {
        return table.isPositionNotOccupied(position.stepToDirection(forward));
    }

    private boolean canMoveTwoSquareForward(Table table) {
        return table.isPositionNotOccupied(position.stepToDirection(forward).stepToDirection(forward)) &&
                isStartingRow(position);
    }

    private void addAttackMovement(Table table, List<Position> validMoves) {
        if (canAttackForwardRight(table)) {
            validMoves.add(position.stepToDirection(forwardRight));
        }
        if (canAttackForwardLeft(table)) {
            validMoves.add(position.stepToDirection(forwardLeft));
        }
    }

    private boolean canAttackForwardRight(Table table) {
        return table.isPositionOccupiedByEnemy(position.stepToDirection(forwardRight), color);
    }

    private boolean canAttackForwardLeft(Table table) {
        return table.isPositionOccupiedByEnemy(position.stepToDirection(forwardLeft), color);
    }

    public List<Position> getPossibleAttackMoves() {
        List<Position> moves = new ArrayList<>();

        moves.add(position.stepToDirection(forwardRight));
        moves.add(position.stepToDirection(forwardLeft));

        return moves;
    }

    private boolean isStartingRow(Position position) {
        if (color == Color.BLACK && Position.toString(position).charAt(1) == '7') {
            return true;
        }
        return color == Color.WHITE && Position.toString(position).charAt(1) == '2';
    }

    private void addPossibleEnPassant(List<Position> validMoves) {
        if (canDoEnPassantLeft) {
            validMoves.add(position.stepToDirection(forwardLeft));
        }
        if (canDoEnPassantRight) {
            validMoves.add(position.stepToDirection(forwardRight));
        }
    }

    public boolean isAttackMove(Position moveFrom,Position moveTo){
       return moveFrom.stepToDirection(forwardLeft).equals(moveTo) || moveFrom.stepToDirection(forwardRight).equals(moveTo);
    }

    public void makeEnPassantFalse() {
        canDoEnPassantLeft = false;
        canDoEnPassantRight = false;
    }


    @Override
    public String getFigureAsString() {
        if (color == Color.WHITE) {
            return " WP ";
        }
        return " BP ";
    }

}
