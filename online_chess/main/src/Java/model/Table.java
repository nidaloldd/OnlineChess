package Java.model;

import Java.model.figures.*;

import Java.model.figures.*;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Table {
    public static final int TABLE_SIZE = 8;
    private Color activePlayerColor = Color.WHITE;
    private King whiteKing;
    private King blackKing;
    private final List<Figure> figures = new ArrayList<>();
    private final List<Figure> takenFigures = new ArrayList<>();
    public List<Figure> getFigures() {
        return figures;
    }

    public King getKing(Color color){
        if(color == Color.WHITE){
            return whiteKing;
        }
        else {
            return  blackKing;
        }
    }
    public Color getActivePlayerColor() {
        return activePlayerColor;
    }

    public Figure getFigureOn(Position position){
        for (Figure f : figures){
            if(position.equals(f.getPosition())){
                return f;
            }
        }
        return null;
    }
    public Figure getFigureOn(String strPos){
        Position position = Position.toPosition(strPos);
        return getFigureOn(position);
    }
    public void setFigureOn(Position position,Figure figure){
        this.figures.remove(getFigureOn(position));
        this.figures.add(figure);
    }
    public Table(){
        setUpStartingTable();
    }

    private boolean isContainsTheTwoKing(List<Figure> figures){
        if(figures.isEmpty()){return false;}
        int whiteKingNumber = 0;
        int blackKingNumber = 0;
        for(Figure figure : figures){
            if(figure instanceof King ){
                if(figure.getColor()==Color.WHITE){
                    whiteKingNumber += 1;
                }
                else {
                    blackKingNumber += 1;
                }
                if(whiteKingNumber > 1 || blackKingNumber>1){ return false;}
            }
        }
        return whiteKingNumber != 0 && blackKingNumber != 0;
    }
    public Table(List<Figure> figures){
        if(!isContainsTheTwoKing(figures)){return;}
        figures.clear();
        this.figures.addAll(figures);
    }
    private void setUpStartingTable(){
        figures.clear();
        whiteKing = (new King(this,Color.WHITE,"E1"));
        blackKing = (new King(this,Color.BLACK,"E8"));

        figures.add((new Rook(this, Color.WHITE,"A1")));
        figures.add((new Knight(this,Color.WHITE,"B1")));
        figures.add((new Bishop(this,Color.WHITE,"C1")));
        figures.add((new Queen(this,Color.WHITE,"D1")));
        figures.add(whiteKing);
        figures.add((new Bishop(this,Color.WHITE,"F1")));
        figures.add((new Knight(this,Color.WHITE,"G1")));
        figures.add((new Rook(this,Color.WHITE,"H1")));

        figures.add((new Pawn(this,Color.WHITE,"A2")));
        figures.add((new Pawn(this,Color.WHITE,"B2")));
        figures.add((new Pawn(this,Color.WHITE,"C2")));
        figures.add((new Pawn(this,Color.WHITE,"D2")));
        figures.add((new Pawn(this,Color.WHITE,"E2")));
        figures.add((new Pawn(this,Color.WHITE,"F2")));
        figures.add((new Pawn(this,Color.WHITE,"G2")));
        figures.add((new Pawn(this,Color.WHITE,"H2")));

        figures.add((new Rook(this,Color.BLACK,"A8")));
        figures.add((new Knight(this,Color.BLACK,"B8")));
        figures.add((new Bishop(this,Color.BLACK,"C8")));
        figures.add((new Queen(this,Color.BLACK,"D8")));
        figures.add(blackKing);
        figures.add((new Bishop(this,Color.BLACK,"F8")));
        figures.add((new Knight(this,Color.BLACK,"G8")));
        figures.add((new Rook(this,Color.BLACK,"H8")));

        figures.add((new Pawn(this,Color.BLACK,"A7")));
        figures.add((new Pawn(this,Color.BLACK,"B7")));
        figures.add((new Pawn(this,Color.BLACK,"C7")));
        figures.add((new Pawn(this,Color.BLACK,"D7")));
        figures.add((new Pawn(this,Color.BLACK,"E7")));
        figures.add((new Pawn(this,Color.BLACK,"F7")));
        figures.add((new Pawn(this,Color.BLACK,"G7")));
        figures.add((new Pawn(this,Color.BLACK,"H7")));
    }

    public Boolean isKingInCheck(Color color){

        return getEnemyValidMoves(color).contains(getKing(color).getPosition());

    }
    public List<Position> getEnemyValidMoves(Color color){
        List<Position> enemyMoves = new ArrayList<>();
        for(Figure figure : figures){
            if(figure.getColor() == Color.getOpposite(color)){
                if(figure instanceof King){
                    for(Direction direction : Direction.getAllDirections()){
                        enemyMoves.addAll(figure.getValidMovesFromOneDirectionOnlyOneStep(direction));
                    }
                }
                else if(figure instanceof Pawn){
                    enemyMoves.addAll(((Pawn) figure).getPossibleAttackMoves());
                }
                else {
                    enemyMoves.addAll(figure.getValidMoves(false));
                }

            }
        }
        return enemyMoves;
    }
    private boolean isMoveValid(Position moveFrom, Position moveTo){
        if(getFigureOn(moveFrom)==null){System.out.println("There is no Figure on Position "+ Position.toString(moveFrom)); return false; }
        if(getFigureOn(moveFrom).getColor() != activePlayerColor){System.out.println("Other Player Turn");return false;}
        List<Position> validMoves = getFigureOn(moveFrom).getValidMoves();
        if(!validMoves.contains(moveTo)){System.out.println(moveTo.toString()+" is Not Valid move"); return false;}
        return true;
    }

    public void  makeMove(Position moveFrom, Position moveTo){
        if(!isMoveValid(moveFrom,moveTo)){return;}

        if(isPositionOccupiedByEnemy(moveTo,activePlayerColor)){
            takeFigure(getFigureOn(moveTo));
        }
        handleCastle(moveFrom,moveTo);
        handleEnPassant(moveFrom,moveTo);
        handlePawnPromotion(moveFrom,moveTo);

        getFigureOn(moveFrom).setPosition(moveTo);

        activePlayerColor = Color.getOpposite(activePlayerColor);
    }
    private String[] getSmallCastleMove(Color color){
        if(color == Color.WHITE){
            return new String[]{"E1", "G1"};
        }else {
            return new String[]{"E8", "G8"};
        }
    }
    private String[] getBigCastleMove(Color color){
        if(color == Color.WHITE){
            return new String[]{"E1", "C1"};
        }else {
            return new String[]{"E8", "C8"};
        }
    }
    private boolean isBigCastle(Position moveFrom,Position moveTo,Color color){

        return moveFrom.equals(Position.toPosition(getBigCastleMove(color)[0])) &&
                moveTo.equals(Position.toPosition(getBigCastleMove(color)[1]));
    }

    private boolean isSmallCastle(Position moveFrom,Position moveTo,Color color){

        return moveFrom.equals(Position.toPosition(getSmallCastleMove(color)[0])) &&
                moveTo.equals(Position.toPosition(getSmallCastleMove(color)[1]));
    }

    private void handleCastle(Position moveFrom, Position moveTo) {
        if(!(getFigureOn(moveFrom) instanceof King)){return;}

        if(isSmallCastle(moveFrom,moveTo,Color.WHITE)){
            getFigureOn(Position.toPosition("H1")).setPosition(Position.toPosition("F1"));
        }
        else if(isBigCastle(moveFrom,moveTo,Color.WHITE)){
            getFigureOn(Position.toPosition("A1")).setPosition(Position.toPosition("D1"));
        }
        else if(isSmallCastle(moveFrom,moveTo,Color.BLACK)){
            getFigureOn(Position.toPosition("H8")).setPosition(Position.toPosition("F8"));
        }
        else if(isBigCastle(moveFrom,moveTo,Color.BLACK)){
            getFigureOn(Position.toPosition("A8")).setPosition(Position.toPosition("D8"));
        }
    }
    private void takeFigure(Figure figure){
        takenFigures.add(figure);
        figures.remove(figure);
    }

    private void handlePawnPromotion(Position moveFrom, Position moveTo){
        if(!(getFigureOn(moveFrom) instanceof Pawn pawn)){return;}

        if(pawn.isLastRow(moveTo)){
            setFigureOn(moveTo,new Queen(this,activePlayerColor,Position.toString(moveTo)));
        }
    }
    private void handleEnPassant(Position moveFrom, Position moveTo){
        for(Figure figure : figures){
            if(figure instanceof Pawn){
                ((Pawn) figure).makeEnPassantFalse();
            }
        }
        if(!(getFigureOn(moveFrom) instanceof Pawn)){return;}

        if(Math.abs(moveFrom.getPosY()-moveTo.getPosY()) == 2){
            var rightNeighbor = getFigureOn(moveTo.stepToDirection(Direction.RIGHT));
            var leftNeighbor = getFigureOn(moveTo.stepToDirection(Direction.LEFT));

                if( rightNeighbor instanceof Pawn && rightNeighbor.getColor() == Color.getOpposite(activePlayerColor)){
                    ((Pawn) rightNeighbor).canDoEntPassantLeft = true;
                }

                if(leftNeighbor instanceof Pawn && leftNeighbor.getColor() == Color.getOpposite(activePlayerColor)){
                    ((Pawn) leftNeighbor).canDoEntPassantRight = true;

                }
        }

        var back = Direction.getDirectionForward(Color.getOpposite(activePlayerColor));
        if(isEnPassantMove(moveFrom,moveTo)){
            takeFigure(getFigureOn(moveTo.stepToDirection(back)));
        }
    }
    private boolean isEnPassantMove(Position moveFrom,Position moveTo){
        var forwardLeft = Direction.getDirectionForwardLeft(activePlayerColor);
        var forwardRight = Direction.getDirectionForwardRight(activePlayerColor);
        var back = Direction.getDirectionForward(Color.getOpposite(activePlayerColor));
        return moveTo.isEmpty(this) &&
                (moveFrom.stepToDirection(forwardLeft).equals(moveTo) || moveFrom.stepToDirection(forwardRight).equals(moveTo)) &&
                getFigureOn(moveTo.stepToDirection(back)) instanceof Pawn &&
                getFigureOn(moveTo.stepToDirection(back)).getColor() == Color.getOpposite(activePlayerColor);
    }
    public void  makeMove(String str){
        Pattern pattern = Pattern.compile("^O-O|O-O-O|[KQBNR][a-h][1-8]x?[a-h][1-8][\\+\\#]?|[a-h][1-8]x?[a-h][1-8][\\+\\#]?|[1-8]x?[a-h][1-8][\\+\\#]?|[a-h]x?[a-h][1-8][\\+\\#]?|[KQBNR][1-8]x?[a-h][1-8][\\+\\#]?|[KQBNR][a-h]x?[a-h][1-8][\\+\\#]?|[KQBNR]x?[a-h][1-8][\\+\\#]?|[a-h][1-8]x?[\\+\\#]?", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(str);
        boolean isValidInput = matcher.find();
        if(!isValidInput) {
            System.out.println("Match not found: "+str);
            return;
        }

        StringBuilder chessNotation = new StringBuilder(str);
        Position moveFrom ;
        Position moveTo;

        System.out.println(chessNotation);
        if(str.equals("O-O")){
            moveFrom = Position.toPosition(getSmallCastleMove(activePlayerColor)[0]);
            moveTo = Position.toPosition( getSmallCastleMove(activePlayerColor)[1] );
            makeMove(moveFrom,moveTo);
            return;
        }
        else if(str.equals("O-O-O")){
            moveFrom = Position.toPosition( getBigCastleMove(activePlayerColor)[0] );
            moveTo = Position.toPosition( getBigCastleMove(activePlayerColor)[1] );
            makeMove(moveFrom,moveTo);
            return;
        }

        Class<?> typeOfFigure = Object.class;
        if(Character.isLowerCase(str.charAt(0))){
            typeOfFigure = Pawn.class;
        }
        else {
            typeOfFigure = Figure.getFigureTypeBy(chessNotation.charAt(0));
            chessNotation.deleteCharAt(0);
        }

        if(chessNotation.indexOf("x")!=-1){
            chessNotation.deleteCharAt(chessNotation.indexOf("x"));
        }
        if(chessNotation.indexOf("+")!=-1){
            chessNotation.deleteCharAt(chessNotation.indexOf("+"));
        }
        if(chessNotation.indexOf("#")!=-1){
            chessNotation.deleteCharAt(chessNotation.indexOf("#"));
        }

        String sub = chessNotation.substring(chessNotation.length()-2,chessNotation.length());

        moveTo = Position.toPosition(sub);
        chessNotation.delete(chessNotation.length()-2,chessNotation.length());

        moveFrom = findTheCorrectFigurePosition(typeOfFigure,chessNotation.toString(),moveTo);

        System.out.println("From: "+ Position.toString(moveFrom)+" To: "+Position.toString( moveTo) );
        makeMove(moveFrom, moveTo);
    }
    private Position findTheCorrectFigurePosition(Class<?> typeOfFigure, String matchingAngle, Position moveTo){
        for(Figure f : figures){
            if( f.getClass().equals(typeOfFigure)&& f.getColor() == activePlayerColor && f.getValidMoves().contains(moveTo)){
                switch (matchingAngle.length()){
                    case 0: return f.getPosition();
                    case 1:
                        if(Character.isAlphabetic(matchingAngle.charAt(0))&&
                                f.getPosition().getPosX()==Position.ranksToPos.get(Character.toUpperCase(matchingAngle.charAt(0)))){
                            return  f.getPosition();
                        }
                        if(Character.isDigit(matchingAngle.charAt(0))&&
                                f.getPosition().getPosY()==Position.filesToPos.get(Character.toUpperCase(matchingAngle.charAt(0)))){
                            return f.getPosition();

                        }
                    case 2:
                        if(f.getPosition().getPosX()==Position.ranksToPos.get(Character.toUpperCase(matchingAngle.charAt(0))) &&
                                f.getPosition().getPosY()==Position.filesToPos.get(matchingAngle.charAt(1))){
                            return f.getPosition();
                        }
                }
            }
        }
        return new Position(-1,-1);
    }

    public boolean isPositionNotOccupied(Position position){
        return getFigureOn(position) == null;
    }
    public boolean isPositionOccupiedByEnemy(Position position, Color color){
        if(isPositionNotOccupied(position)){return false;}

        return getFigureOn(position).getColor() == Color.getOpposite(color);
    }
}