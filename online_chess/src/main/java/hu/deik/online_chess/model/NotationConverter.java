package hu.deik.online_chess.model;

import hu.deik.online_chess.model.figures.Figure;
import hu.deik.online_chess.model.figures.Pawn;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class NotationConverter {

    public static String positionsToNotation(Table table, Position moveFrom, Position moveTo) {
        if (moveFrom.isPositionNotValid() || moveTo.isPositionNotValid()) {
            return " ";
        }
        Figure moveFromFigure = table.getFigureOn(moveFrom);

        if (table.isSmallCastle(moveFrom, moveTo, moveFromFigure.getColor())) {
            return "O-O";
        }
        if (table.isBigCastle(moveFrom, moveTo, moveFromFigure.getColor())) {
            return "O-O-O";
        }

        boolean isCapture = table.getFigureOn(moveTo) != null;
        String result = "";

        String baseCharacter = addBaseCharacter(moveFromFigure);
        String moveFromCoordinates = addMoveFromCoordinates(table, moveFrom, moveTo);
        String captureMark = addCaptureMark(isCapture);
        String moveToCoordinates = Position.toString(moveTo).toLowerCase();
        String checkMark = addInCheckMark(table, moveFrom, moveTo);

        if (moveFromCoordinates == "" && captureMark != "" && moveFromFigure instanceof Pawn) {
            moveFromCoordinates += Position.toString(moveFromFigure.getPosition()).substring(0, 1).toLowerCase();
        }

        return baseCharacter + moveFromCoordinates + captureMark + moveToCoordinates + checkMark;
    }

    private static String addMoveFromCoordinates(Table table, Position moveFrom, Position moveTo) {
        Figure moveFromFigure = table.getFigureOn(moveFrom);

        List<Figure> SameTypeFiguresThatCanMoveTheSameSquare = getSameTypeFiguresThatCanMoveTheSameSquare(table, moveFrom, moveTo);

        if (SameTypeFiguresThatCanMoveTheSameSquare.size() >= 2) {
            return Position.toString(moveFrom).toLowerCase();
        } else if (SameTypeFiguresThatCanMoveTheSameSquare.size() == 1) {
            return addRowOrColumMark(SameTypeFiguresThatCanMoveTheSameSquare.get(0), moveFromFigure);
        } else {
            return "";
        }

    }

    private static String addRowOrColumMark(Figure figure1, Figure moveFromFigure) {
        String xMark = Position.toString(moveFromFigure.getPosition()).substring(0, 1).toLowerCase();
        String yMark = Position.toString(moveFromFigure.getPosition()).substring(1, 2);
        if (haveSameYAngle(figure1, moveFromFigure)) {
            return xMark;
        } else if (haveSameXAngle(figure1, moveFromFigure)) {
            return yMark;
        } else {
            return xMark;
        }
    }

    private static List<Figure> getSameTypeFiguresThatCanMoveTheSameSquare(Table table, Position moveFrom, Position moveTo) {
        Figure moveFromFigure = table.getFigureOn(moveFrom);
        List<Figure> figures = new ArrayList<>();

        for (int i = 0; i < table.getFigures().size(); i++) {
            boolean sameColor = table.getFigures().get(i).getColor() == moveFromFigure.getColor();
            boolean sameFigureType = table.getFigures().get(i).getClass().equals(moveFromFigure.getClass());
            boolean haveSameValidMove = table.getFigures().get(i).getValidMoves(table).contains(moveTo) && !table.getFigures().get(i).equals(moveFromFigure);

            if (sameColor && sameFigureType && haveSameValidMove) {
                figures.add(table.getFigures().get(i));
            }
        }
        return figures;
    }

    private static String addCaptureMark(boolean isCapture) {
        if (isCapture) {
            return "x";
        }
        return "";
    }

    private static String addInCheckMark(Table table, Position moveFrom, Position moveTo) {
        Figure moveFromFigure = table.getFigureOn(moveFrom);
        Position originalPos = new Position(moveFromFigure.getPosition());
        moveFromFigure.setPosition(moveTo);

        String result = "";
        if (table.handleCheckMate(moveFromFigure.getColor())) {
            result = "#";
        } else if (table.isKingInCheck(Color.getOpposite(moveFromFigure.getColor()))) {
            result = "+";
        }

        moveFromFigure.setPosition(originalPos);
        return result;
    }

    private static boolean haveSameYAngle(Figure figure1, Figure figure2) {
        return figure1.getPosition().getPosY() == figure2.getPosition().getPosY();
    }

    private static boolean haveSameXAngle(Figure figure1, Figure figure2) {
        return figure1.getPosition().getPosX() == figure2.getPosition().getPosX();
    }

    private static String addBaseCharacter(Figure figure) {
        if (!(figure instanceof Pawn)) {
            return String.valueOf(Figure.getCharacterBy(figure.getClass()));
        }
        return "";
    }

    public static Position[] notationToPositions(Table table, String str) {
        Pattern pattern = Pattern.compile("^O-O|O-O-O|[KQBNR][a-h][1-8]x?[a-h][1-8][\\+\\#]?|[a-h][1-8]x?[a-h][1-8][\\+\\#]?|[1-8]x?[a-h][1-8][\\+\\#]?|[a-h]x?[a-h][1-8][\\+\\#]?|[KQBNR][1-8]x?[a-h][1-8][\\+\\#]?|[KQBNR][a-h]x?[a-h][1-8][\\+\\#]?|[KQBNR]x?[a-h][1-8][\\+\\#]?|[a-h][1-8]x?[\\+\\#]?", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(str);
        boolean isValidInput = matcher.find();
        if (!isValidInput) {
            log.info("Match not found: " + str);
            return new Position[]{};
        }

        StringBuilder chessNotation = new StringBuilder(str);
        Position moveFrom;
        Position moveTo;

        if (str.equals("O-O")) {
            moveFrom = Position.toPosition(table.getSmallCastleMove(table.getActivePlayerColor())[0]);
            moveTo = Position.toPosition(table.getSmallCastleMove(table.getActivePlayerColor())[1]);
            return new Position[]{moveFrom, moveTo};
        } else if (str.equals("O-O-O")) {
            moveFrom = Position.toPosition(table.getBigCastleMove(table.getActivePlayerColor())[0]);
            moveTo = Position.toPosition(table.getBigCastleMove(table.getActivePlayerColor())[1]);
            return new Position[]{moveFrom, moveTo};
        }

        Class<?> typeOfFigure;
        if (Character.isLowerCase(str.charAt(0))) {
            typeOfFigure = Pawn.class;
        } else {
            typeOfFigure = Figure.getFigureTypeBy(chessNotation.charAt(0));
            chessNotation.deleteCharAt(0);
        }

        if (chessNotation.indexOf("x") != -1) {
            chessNotation.deleteCharAt(chessNotation.indexOf("x"));
        }
        if (chessNotation.indexOf("+") != -1) {
            chessNotation.deleteCharAt(chessNotation.indexOf("+"));
        }
        if (chessNotation.indexOf("#") != -1) {
            chessNotation.deleteCharAt(chessNotation.indexOf("#"));
        }

        String sub = chessNotation.substring(chessNotation.length() - 2, chessNotation.length());

        moveTo = Position.toPosition(sub);
        chessNotation.delete(chessNotation.length() - 2, chessNotation.length());

        moveFrom = findTheCorrectFigurePosition(table, typeOfFigure, chessNotation.toString(), moveTo);

        return new Position[]{moveFrom, moveTo};
    }

    private static Position findTheCorrectFigurePosition(Table table, Class<?> typeOfFigure, String matchingAngle, Position moveTo) {
        for (int i = 0; i < table.getFigures().size(); i++) {

            if (table.getFigures().get(i).getClass().equals(typeOfFigure) && table.getFigures().get(i).getColor() == table.getActivePlayerColor() && table.getFigures().get(i).getValidMoves(table).contains(moveTo)) {
                switch (matchingAngle.length()) {
                    case 0:
                        return table.getFigures().get(i).getPosition();
                    case 1:
                        if (Character.isAlphabetic(matchingAngle.charAt(0)) &&
                                table.getFigures().get(i).getPosition().getPosX() == Position.ranksToPos.get(Character.toUpperCase(matchingAngle.charAt(0)))) {
                            return table.getFigures().get(i).getPosition();
                        }
                        if (Character.isDigit(matchingAngle.charAt(0)) &&
                                table.getFigures().get(i).getPosition().getPosY() == Position.filesToPos.get(Character.toUpperCase(matchingAngle.charAt(0)))) {
                            return table.getFigures().get(i).getPosition();
                        }
                        break;
                    case 2:
                        if (table.getFigures().get(i).getPosition().getPosX() == Position.ranksToPos.get(Character.toUpperCase(matchingAngle.charAt(0))) &&
                                table.getFigures().get(i).getPosition().getPosY() == Position.filesToPos.get(matchingAngle.charAt(1))) {
                            return table.getFigures().get(i).getPosition();
                        }
                        break;
                    default:
                }
            }
        }

        return new Position(-1, -1);
    }
}