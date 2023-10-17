package hu.deik.online_chess.model;

import hu.deik.online_chess.model.Draw.DrawTable;
import hu.deik.online_chess.model.figures.*;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class Table {
    public static final int TABLE_SIZE = 8;
    private boolean isGameOver;
    public StringBuilder gameNotation;
    private Color activePlayerColor;
    private King whiteKing;
    private King blackKing;
    private int numberOfMoves;
    private List<Figure> figures = new ArrayList<>();
    private List<Figure> takenFigures = new ArrayList<>();

    public Table() {
        setUpStartingTable();
    }

    public Table(List<Figure> figures, Color activePlayerColor) {
        this.isGameOver = false;
        this.gameNotation = new StringBuilder("");
        this.numberOfMoves = 0;
        this.takenFigures.clear();
        this.figures.clear();
        this.figures.addAll(figures);
        this.activePlayerColor = activePlayerColor;

        for (Figure figure : figures) {
            if (!(figure instanceof King)) {
                continue;
            }

            if (figure.getColor() == Color.WHITE) {
                this.whiteKing = (King) figure;
            } else {
                this.blackKing = (King) figure;
            }
        }
    }

    private void setUpStartingTable() {
        isGameOver = false;
        gameNotation = new StringBuilder("");
        figures.clear();
        takenFigures.clear();
        numberOfMoves = 0;
        activePlayerColor = Color.WHITE;
        whiteKing = (new King(Color.WHITE, "E1"));
        blackKing = (new King(Color.BLACK, "E8"));

        figures.add((new Rook(Color.WHITE, "A1")));
        figures.add((new Knight(Color.WHITE, "B1")));
        figures.add((new Bishop(Color.WHITE, "C1")));
        figures.add((new Queen(Color.WHITE, "D1")));
        figures.add(whiteKing);
        figures.add((new Bishop(Color.WHITE, "F1")));
        figures.add((new Knight(Color.WHITE, "G1")));
        figures.add((new Rook(Color.WHITE, "H1")));

        figures.add((new Pawn(Color.WHITE, "A2")));
        figures.add((new Pawn(Color.WHITE, "B2")));
        figures.add((new Pawn(Color.WHITE, "C2")));
        figures.add((new Pawn(Color.WHITE, "D2")));
        figures.add((new Pawn(Color.WHITE, "E2")));
        figures.add((new Pawn(Color.WHITE, "F2")));
        figures.add((new Pawn(Color.WHITE, "G2")));
        figures.add((new Pawn(Color.WHITE, "H2")));

        figures.add((new Rook(Color.BLACK, "A8")));
        figures.add((new Knight(Color.BLACK, "B8")));
        figures.add((new Bishop(Color.BLACK, "C8")));
        figures.add((new Queen(Color.BLACK, "D8")));
        figures.add(blackKing);
        figures.add((new Bishop(Color.BLACK, "F8")));
        figures.add((new Knight(Color.BLACK, "G8")));
        figures.add((new Rook(Color.BLACK, "H8")));

        figures.add((new Pawn(Color.BLACK, "A7")));
        figures.add((new Pawn(Color.BLACK, "B7")));
        figures.add((new Pawn(Color.BLACK, "C7")));
        figures.add((new Pawn(Color.BLACK, "D7")));
        figures.add((new Pawn(Color.BLACK, "E7")));
        figures.add((new Pawn(Color.BLACK, "F7")));
        figures.add((new Pawn(Color.BLACK, "G7")));
        figures.add((new Pawn(Color.BLACK, "H7")));
    }

    public void makeMove(String notation) {
        Position[] pos = NotationConverter.notationToPositions(this, notation);
        makeMove(pos[0], pos[1]);
    }

    public void makeMove(String moveFrom, String moveTo) {
        makeMove(Position.toPosition(moveFrom), Position.toPosition(moveTo));
    }

    public void makeMove(Position moveFrom, Position moveTo) {
        if (!canMove(moveFrom, moveTo)) {
            return;
        }

        handleTakeFigure(moveTo);
        handleCastle(moveFrom, moveTo);
        handleEnPassant(moveFrom, moveTo);
        handlePawnPromotion(moveFrom, moveTo);
        isGameOver = handleCheckMate(activePlayerColor);
        addMoveToGameNotation(moveFrom, moveTo);
        getFigureOn(moveFrom).setPosition(moveTo);


        if (!isGameOver) {
            activePlayerColor = Color.getOpposite(activePlayerColor);
        }

        log.info(DrawTable.makeTableToString(this));
    }

    private void addMoveToGameNotation(Position moveFrom, Position moveTo) {
        if (activePlayerColor == Color.WHITE) {
            gameNotation.append(++numberOfMoves + ": ");
            gameNotation.append(NotationConverter.positionsToNotation(this, moveFrom, moveTo));
        } else {
            gameNotation.append(" - ");
            gameNotation.append(NotationConverter.positionsToNotation(this, moveFrom, moveTo));
            gameNotation.append("\n");
        }

    }

    private void handleTakeFigure(Position moveTo) {
        if (isPositionOccupiedByEnemy(moveTo, activePlayerColor)) {
            takeFigure(moveTo);
        }
    }

    private void handleCastle(Position moveFrom, Position moveTo) {
        if (!isKing(moveFrom)) {
            return;
        }

        handleRookMovementWhileCastle(moveFrom, moveTo);
    }

    private void handleRookMovementWhileCastle(Position kingMoveFrom, Position kingMoveTo) {
        if (isSmallCastle(kingMoveFrom, kingMoveTo, Color.WHITE)) {
            getFigureOn(Position.toPosition("H1")).setPosition(Position.toPosition("F1"));
        } else if (isBigCastle(kingMoveFrom, kingMoveTo, Color.WHITE)) {
            getFigureOn(Position.toPosition("A1")).setPosition(Position.toPosition("D1"));
        } else if (isSmallCastle(kingMoveFrom, kingMoveTo, Color.BLACK)) {
            getFigureOn(Position.toPosition("H8")).setPosition(Position.toPosition("F8"));
        } else if (isBigCastle(kingMoveFrom, kingMoveTo, Color.BLACK)) {
            getFigureOn(Position.toPosition("A8")).setPosition(Position.toPosition("D8"));
        }
    }

    boolean isSmallCastle(Position moveFrom, Position moveTo, Color color) {

        return moveFrom.equals(Position.toPosition(getSmallCastleMove(color)[0])) &&
                moveTo.equals(Position.toPosition(getSmallCastleMove(color)[1]));
    }

    boolean isBigCastle(Position moveFrom, Position moveTo, Color color) {

        return moveFrom.equals(Position.toPosition(getBigCastleMove(color)[0])) &&
                moveTo.equals(Position.toPosition(getBigCastleMove(color)[1]));
    }

    private void handleEnPassant(Position moveFrom, Position moveTo) {
        if (!(getFigureOn(moveFrom) instanceof Pawn)) {
            return;
        }
        setAllPawnsFalseToEnPassant();
        setPawnToBeAbleToEnPassant(moveFrom, moveTo);
        takeEnemyPawnIfEnPassant(moveFrom, moveTo);
    }

    private void setAllPawnsFalseToEnPassant() {
        for (Figure figure : figures) {
            if (figure instanceof Pawn) {
                ((Pawn) figure).makeEnPassantFalse();
            }
        }
    }

    private void setPawnToBeAbleToEnPassant(Position moveFrom, Position moveTo) {
        if (isDoubleForwardMove(moveFrom, moveTo)) {
            Figure rightNeighbor = getFigureOn(moveTo.stepToDirection(Direction.RIGHT));
            Figure leftNeighbor = getFigureOn(moveTo.stepToDirection(Direction.LEFT));

            if (isEnemyPawn(rightNeighbor)) {
                ((Pawn) rightNeighbor).setCanDoEnPassantLeft(true);
            }

            if (isEnemyPawn(leftNeighbor)) {
                ((Pawn) leftNeighbor).setCanDoEnPassantRight(true);

            }
        }
    }

    private void takeEnemyPawnIfEnPassant(Position moveFrom, Position moveTo) {
        Direction back = Direction.getDirectionForward(Color.getOpposite(activePlayerColor));
        if (isEnPassantMove(moveFrom, moveTo)) {
            takeFigure(moveTo.stepToDirection(back));
        }
    }

    public boolean handleCheckMate(Color color) {
        for (Figure opponentFigure : getOpponentsFigures(color)) {
            if (!opponentFigure.getValidMoves(this).isEmpty()) {
                return false;
            }
        }
        return true;
    }

    private List<Figure> getOpponentsFigures(Color color) {
        List<Figure> opponentsFigures = new ArrayList<>();
        for (Figure figure : figures) {
            if (figure.getColor() == Color.getOpposite(color)) {
                opponentsFigures.add(figure);
            }
        }
        return opponentsFigures;
    }

    public boolean isKingInCheck(Color color) {
        return getOpponentsPossibleMoves(color).contains(getKing(color).getPosition());
    }

    public List<Position> getOpponentsPossibleMoves(Color color) {
        List<Position> enemyMoves = new ArrayList<>();
        for (Figure opponentFigure : getOpponentsFigures(color)) {

            if (opponentFigure instanceof King) {
                enemyMoves.addAll(((King) opponentFigure).getValidMoveOnlyOneStepToAllDirections(this));
            } else if (opponentFigure instanceof Pawn) {
                enemyMoves.addAll(((Pawn) opponentFigure).getPossibleAttackMoves());
            } else {
                enemyMoves.addAll(opponentFigure.getAllPossibleMoves(this));
            }

        }
        return enemyMoves;
    }


    private void handlePawnPromotion(Position moveFrom, Position moveTo) {
        if (!(getFigureOn(moveFrom) instanceof Pawn)) {
            return;
        }

        if (moveTo.isLastRow(getActivePlayerColor())) {
            setFigureOn(moveFrom, new Queen(activePlayerColor, Position.toString(moveFrom)));
        }
    }

    private boolean canMove(Position moveFrom, Position moveTo) {
        return isMoveValid(moveFrom, moveTo) && !isGameOver;
    }

    private boolean isMoveValid(Position moveFrom, Position moveTo) {
        if (getFigureOn(moveFrom) == null) {
            log.info("There is no Figure on Position " + Position.toString(moveFrom));
            return false;
        }
        if (getFigureOn(moveFrom).getColor() != activePlayerColor) {
            log.info("Other Player Turn");
            return false;
        }

        List<Position> validMoves = getFigureOn(moveFrom).getValidMoves(this);
        if (!validMoves.contains(moveTo)) {
            log.info(moveTo.toString() + " is Not Valid move");
            return false;
        }
        return true;
    }


    String[] getSmallCastleMove(Color color) {
        if (color == Color.WHITE) {
            return new String[]{"E1", "G1"};
        } else {
            return new String[]{"E8", "G8"};
        }
    }

    String[] getBigCastleMove(Color color) {
        if (color == Color.WHITE) {
            return new String[]{"E1", "C1"};
        } else {
            return new String[]{"E8", "C8"};
        }
    }


    private boolean isKing(Position position) {
        return getFigureOn(position) instanceof King;
    }

    private void takeFigure(Position position) {
        Figure figure = getFigureOn(position);
        takenFigures.add(figure);
        figures.remove(figure);

    }


    private boolean isEnemyPawn(Figure figure) {
        return figure instanceof Pawn && figure.getColor() == Color.getOpposite(activePlayerColor);
    }


    private boolean isDoubleForwardMove(Position moveFrom, Position moveTo) {
        return Math.abs(moveFrom.getPosY() - moveTo.getPosY()) == 2;
    }

    private boolean isEnPassantMove(Position moveFrom, Position moveTo) {
        Direction back = Direction.getDirectionForward(Color.getOpposite(activePlayerColor));
        Pawn pawn = (Pawn) getFigureOn(moveFrom);

        return moveTo.isEmpty(this) &&
                pawn.isAttackMove(moveFrom, moveTo) &&
                isEnemyPawn(getFigureOn(moveTo.stepToDirection(back)));

    }

    public boolean isPositionNotOccupied(Position position) {
        return getFigureOn(position) == null;
    }

    public boolean isPositionOccupiedByEnemy(Position position, Color color) {
        if (isPositionNotOccupied(position)) {
            return false;
        }

        return getFigureOn(position).getColor() == Color.getOpposite(color);
    }

    public boolean isPositionOccupiedByAlly(Position position, Color color) {
        if (isPositionNotOccupied(position)) {
            return false;
        }

        return getFigureOn(position).getColor() == color;
    }

    public List<Figure> getFigures() {
        return figures;
    }

    public void setFigures(List<Figure> figures) {
        this.figures = figures;
    }

    public List<Figure> getTakenFigures() {
        return takenFigures;
    }

    public void setTakenFigures(List<Figure> takenFigures) {
        this.takenFigures = takenFigures;
    }


    public King getKing(Color color) {
        if (color == Color.WHITE) {
            return whiteKing;
        } else {
            return blackKing;
        }
    }


    public Color getActivePlayerColor() {
        return activePlayerColor;
    }

    public Figure getFigureOn(Position position) {
        for (Figure f : figures) {
            if (position.equals(f.getPosition())) {
                return f;
            }
        }
        return null;
    }

    public Figure getFigureOn(String strPos) {
        Position position = Position.toPosition(strPos);
        return getFigureOn(position);
    }

    public void setFigureOn(Position position, Figure figure) {
        this.figures.remove(getFigureOn(position));
        this.figures.add(figure);
    }

    public boolean isGameOver() {
        return isGameOver;
    }

    public void setGameOver(boolean gameOver) {
        isGameOver = gameOver;
    }

}