package hu.deik.online_chess.model.figures;

import hu.deik.online_chess.model.Color;
import hu.deik.online_chess.model.Direction;
import hu.deik.online_chess.model.Draw.DrawFigure;
import hu.deik.online_chess.model.Position;
import hu.deik.online_chess.model.Table;

import java.util.ArrayList;
import java.util.List;


public abstract class Figure {
    public final String imageSource;
    private boolean isFigureMoved;
    protected Color color;
    protected Position position;

    protected Figure(Color color, Position position) {
        this.color = color;
        this.position = position;
        isFigureMoved = false;
        imageSource = DrawFigure.getPNG(this);
    }

    public List<Position> getValidMoves(Table table) {
        return removeKingInCheckMoves(table, getAllPossibleMoves(table));
    }

    public List<Position> removeKingInCheckMoves(Table table, List<Position> validMoves) {

        List<Position> forRemove = new ArrayList<>();

        for (Position squareToMove : validMoves) {
            simulateMoveToRemoveKingInCheckMoves(table, squareToMove, forRemove);
        }
        validMoves.removeAll(forRemove);
        return validMoves;
    }

    private void simulateMoveToRemoveKingInCheckMoves(Table table, Position squareToMove, List<Position> forRemove) {
        Position startingPos = new Position(position.getPosX(), position.getPosY());
        List<Figure> originalFigures = new ArrayList<>(table.getFigures());

        removeFigureIfThereIsAPossibleTake(table, squareToMove);

        position = squareToMove;
        if (table.isKingInCheck(color)) {
            forRemove.add(squareToMove);
        }

        table.setFigures(originalFigures);
        position = startingPos;
    }

    private void removeFigureIfThereIsAPossibleTake(Table table, Position squareToMove) {

        if (table.getFigureOn(squareToMove) != null) {
            table.getFigures().remove(table.getFigureOn(squareToMove));
        }

    }

    public abstract List<Position> getAllPossibleMoves(Table table);

    public List<Position> getValidMovesFromOneDirectionManyStep(Table table, Direction direction) {
        List<Position> validMoves = new ArrayList<>();
        Position actualPosition = position;
        while (true) {
            actualPosition = actualPosition.stepToDirection(direction);
            if (actualPosition.isPositionNotValid()) {
                return validMoves;
            }
            if (table.isPositionOccupiedByEnemy(actualPosition, color)) {
                validMoves.add(actualPosition);
                return validMoves;
            }
            if (table.isPositionNotOccupied(actualPosition)) {
                validMoves.add(actualPosition);
            } else {
                return validMoves;
            }
        }
    }

    public boolean isFigureMoved() {
        return isFigureMoved;
    }

    public abstract String getFigureAsString();

    public static Class<?> getFigureTypeBy(Character character) {

        return switch (character) {
            case 'K' -> King.class;
            case 'Q' -> Queen.class;
            case 'B' -> Bishop.class;
            case 'N' -> Knight.class;
            case 'R' -> Rook.class;
            default -> Object.class;
        };
    }

    public static Character getCharacterBy(Class<?> type) {

        return switch (type.getSimpleName()) {
            case "King" -> 'K';
            case "Queen" -> 'Q';
            case "Bishop" -> 'B';
            case "Knight" -> 'N';
            case "Rook" -> 'R';
            default -> 'X';
        };
    }

    public Color getColor() {
        return color;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        if (!isFigureMoved) {
            isFigureMoved = true;
        }
        this.position = position;
    }

}

