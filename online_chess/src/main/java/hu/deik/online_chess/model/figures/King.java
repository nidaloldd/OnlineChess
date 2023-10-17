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

    public List<Position> getAllPossibleMoves(Table table) {
        List<Position> validMoves = new ArrayList<>();

        validMoves.addAll(getValidMoveOnlyOneStepToAllDirections(table));
        validMoves.addAll(getPossibleCastleMoves(table, validMoves));
        validMoves.removeAll(table.getOpponentsPossibleMoves(color));
        return validMoves;
    }

    public List<Position> getValidMoveOnlyOneStepToAllDirections(Table table) {
        List<Position> validMoves = new ArrayList<>();
        for (Direction direction : Direction.getAllDirections()) {
            Position actualPosition = position;

            actualPosition = actualPosition.stepToDirection(direction);
            if (actualPosition.isPositionValid() && !table.isPositionOccupiedByAlly(actualPosition, color)) {
                validMoves.add(actualPosition);
            }
        }
        return validMoves;
    }

    private List<Position> getPossibleCastleMoves(Table table, List<Position> validMoves) {
        List<Position> result = new ArrayList<>();
        if (!canCastle(table)) {
            return validMoves;
        }

        String row = getStartingRow();

        if (isSmallCastle(table, row, validMoves)) {
            result.add(Position.toPosition("G" + row));
        }
        if (isBigCastle(table, row, validMoves)) {
            result.add(Position.toPosition("C" + row));
        }

        return result;
    }

    private boolean isSmallCastle(Table table, String row, List<Position> validMoves) {

        return (table.getFigureOn("F" + row) == null &&
                table.getFigureOn("G" + row) == null &&
                table.getFigureOn("H" + row) != null &&
                (!table.getFigureOn("H" + row).isFigureMoved()) &&
                validMoves.contains(Position.toPosition("F" + row))
        );
    }

    private boolean isBigCastle(Table table, String row, List<Position> validMoves) {
        return (table.getFigureOn("D" + row) == null &&
                table.getFigureOn("C" + row) == null &&
                table.getFigureOn("B" + row) == null &&
                table.getFigureOn("A" + row) != null &&
                (!table.getFigureOn("A" + row).isFigureMoved()) &&
                validMoves.contains(Position.toPosition("D" + row))
        );
    }

    private boolean canCastle(Table table) {
        return !table.getKing(color).isFigureMoved() && !table.isKingInCheck(color);
    }

    private String getStartingRow() {
        if (color == Color.WHITE) {
            return "1";
        } else {
            return "8";
        }
    }

    @Override
    public String getFigureAsString() {
        if (color == Color.WHITE) {
            return " WK ";
        }
        return " BK ";
    }
}
