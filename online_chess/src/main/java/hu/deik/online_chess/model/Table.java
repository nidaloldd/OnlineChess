package hu.deik.online_chess.model;

import hu.deik.online_chess.model.Draw.DrawTable;
import hu.deik.online_chess.model.figures.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Table {
    public static final int TABLE_SIZE = 8;
    public int numberOfMoves;
    public Boolean isGameOver;
    public  StringBuilder gameNotation;
    private Color activePlayerColor;
    private King whiteKing;
    private King blackKing;
    private List<Figure> figures = new ArrayList<>();
    private List<Figure> takenFigures = new ArrayList<>();
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
    public void setUpStartingTable(){
        isGameOver = false;
        gameNotation = new StringBuilder("");
        figures.clear();
        takenFigures.clear();
        numberOfMoves = 0;
        activePlayerColor = Color.WHITE;
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
    public void handleCheckMate(Color color){
        List<Position> enemyMoves = new ArrayList<>();
        for (int i = 0; i < figures.size(); i++) {
            if(figures.get(i).getColor() == Color.getOpposite(color)){
                if(!figures.get(i).getValidMoves().isEmpty()){
                    return;
                }
            }
        }
        isGameOver = true;
    }
    public List<Position> getEnemyValidMoves(Color color){
        List<Position> enemyMoves = new ArrayList<>();
        for(Figure figure : figures){
            if(figure.getColor() == Color.getOpposite(color)){
                if(figure instanceof King){
                    for(Direction direction : Direction.getAllDirections()){
                        enemyMoves.addAll(figure.getValidMoveFromOneDirectionOnlyOneStep(direction));
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
    private void addMoveToGameNotation(Position moveFrom, Position moveTo){
        if(activePlayerColor == Color.WHITE){
            gameNotation.append(numberOfMoves+": ");
            gameNotation.append(positionsToNotation(moveFrom,moveTo));
        }
        else {
            gameNotation.append(" - ");
            gameNotation.append(positionsToNotation(moveFrom,moveTo));
            gameNotation.append("\n");
        }

    }

    public void  makeMove(Position moveFrom, Position moveTo){
        if(!isMoveValid(moveFrom,moveTo) || isGameOver ){
            return;
        }
        if(activePlayerColor== Color.WHITE){
            numberOfMoves++;
        }
        addMoveToGameNotation(moveFrom,moveTo);

        if(isPositionOccupiedByEnemy(moveTo,activePlayerColor)){
            takeFigure(getFigureOn(moveTo));
        }
        handleCastle(moveFrom,moveTo);
        handleEnPassant(moveFrom,moveTo);
        handlePawnPromotion(moveFrom,moveTo);

        getFigureOn(moveFrom).setPosition(moveTo);

        handleCheckMate(activePlayerColor);

        if(!isGameOver){
            activePlayerColor = Color.getOpposite(activePlayerColor);
        }
        else {
            gameNotation.deleteCharAt(gameNotation.lastIndexOf("+"));
            gameNotation.append("#");
        }

        System.out.println( DrawTable.makeTableToString(this));
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
        if(!(getFigureOn(moveFrom) instanceof Pawn)){return;}

        if(moveTo.isLastRow(getFigureOn(moveFrom).getColor())){
            setFigureOn(moveFrom,new Queen(this,activePlayerColor,Position.toString(moveFrom)));
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

                if( rightNeighbor instanceof Pawn  && rightNeighbor.getColor() == Color.getOpposite(activePlayerColor)){
                    ((Pawn) rightNeighbor).setCanDoEntPassantLeft(true);
                }

                if(leftNeighbor instanceof Pawn  && leftNeighbor.getColor() == Color.getOpposite(activePlayerColor)){
                    ((Pawn) leftNeighbor).setCanDoEntPassantRight(true);

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
    public void makeMove(String str){
        Position[] pos = notationToPositions(str);
        makeMove(pos[0], pos[1]);
    }

    String positionsToNotation(Position moveFrom, Position moveTo){
        if(moveFrom.isPositionNotValid() || moveTo.isPositionNotValid()){return " ";}

        Boolean isCapture = getFigureOn(moveTo) != null;
        Figure moveFromFigure = getFigureOn(moveFrom);
        if(isSmallCastle(moveFrom,moveTo,moveFromFigure.getColor())){
            return "O-O";
        }
        if(isBigCastle(moveFrom,moveTo,moveFromFigure.getColor())){
            return "O-O-O";
        }
        String result = "";

        if(!(moveFromFigure instanceof Pawn)){
            result += Figure.getCharacterBy(moveFromFigure.getClass());
        }

        String xAngle = "";
        String yAngle = "";
        String diagonalAngle = "";
        for(int i = 0;i<figures.size(); i++){
            if(figures.get(i).getColor() == moveFromFigure.getColor() && figures.get(i).getClass().equals(moveFromFigure.getClass()) ){
                System.out.println("Same Color Same Figure");
                if( figures.get(i).getValidMoves(true).contains(moveTo) && !figures.get(i).equals(moveFromFigure)){
                    System.out.println("Same Valid Move ");
                    if(figures.get(i).getPosition().getPosX() == moveFromFigure.getPosition().getPosX() && yAngle.equals("")){
                        System.out.println("Same  getPosX");
                        yAngle = Position.toString(moveFromFigure.getPosition()).substring(1,2);
                    }
                    if((figures.get(i).getPosition().getPosY() == moveFromFigure.getPosition().getPosY() && xAngle.equals("")) ){
                        System.out.println("Same  getPosY");
                        xAngle = Position.toString(moveFromFigure.getPosition()).substring(0,1).toLowerCase();
                    }
                    System.out.println("result "+result);
                    if( result.equals("N") && xAngle.equals("") && yAngle.equals("")  ){
                        System.out.println(" KNIGHT or Pawn");
                        xAngle = Position.toString(moveFromFigure.getPosition()).substring(0,1).toLowerCase();
                    }
                }
                if((result.equals("")&& isCapture)){
                    xAngle = Position.toString(moveFromFigure.getPosition()).substring(0,1).toLowerCase();
                }
            }
        }

        result += xAngle+yAngle;

        if(isCapture){
            result += "x";
        }
        result += Position.toString(moveTo).toLowerCase();

        Position originalPos = new Position(moveFromFigure.getPosition());

        moveFromFigure.setPosition(moveTo);

        //handleCheckMate(moveFromFigure.getColor());
        if(isGameOver){
            result += "#";
        }
        else if(isKingInCheck(Color.getOpposite(moveFromFigure.getColor()))){
                //moveFromFigure.getValidMoves(false).contains(getKing(Color.getOpposite(moveFromFigure.getColor())).getPosition())){
                result += "+";
        }

        moveFromFigure.setPosition(originalPos);
        return result;
    }

    Position[] notationToPositions(String str){
        Pattern pattern = Pattern.compile("^O-O|O-O-O|[KQBNR][a-h][1-8]x?[a-h][1-8][\\+\\#]?|[a-h][1-8]x?[a-h][1-8][\\+\\#]?|[1-8]x?[a-h][1-8][\\+\\#]?|[a-h]x?[a-h][1-8][\\+\\#]?|[KQBNR][1-8]x?[a-h][1-8][\\+\\#]?|[KQBNR][a-h]x?[a-h][1-8][\\+\\#]?|[KQBNR]x?[a-h][1-8][\\+\\#]?|[a-h][1-8]x?[\\+\\#]?", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(str);
        boolean isValidInput = matcher.find();
        if(!isValidInput) {
            System.out.println("Match not found: "+str);
            return new Position[] {};
        }

        StringBuilder chessNotation = new StringBuilder(str);
        Position moveFrom ;
        Position moveTo;

        System.out.println(chessNotation);
        if(str.equals("O-O")){
            moveFrom = Position.toPosition(getSmallCastleMove(activePlayerColor)[0]);
            moveTo = Position.toPosition( getSmallCastleMove(activePlayerColor)[1] );
            return new Position[] {moveFrom,moveTo};
        }
        else if(str.equals("O-O-O")){
            moveFrom = Position.toPosition( getBigCastleMove(activePlayerColor)[0] );
            moveTo = Position.toPosition( getBigCastleMove(activePlayerColor)[1] );
            return new Position[] {moveFrom,moveTo};
        }

        Class<?> typeOfFigure;
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

        return new Position[] {moveFrom,moveTo};
    }
    private Position findTheCorrectFigurePosition(Class<?> typeOfFigure, String matchingAngle, Position moveTo){
            for (int i = 0; i < figures.size() ; i++) {

                if( figures.get(i).getClass().equals(typeOfFigure)&& figures.get(i).getColor() == activePlayerColor && figures.get(i).getValidMoves().contains(moveTo)){
                    switch (matchingAngle.length()){
                        case 0: return figures.get(i).getPosition();
                        case 1:
                            if(Character.isAlphabetic(matchingAngle.charAt(0))&&
                                    figures.get(i).getPosition().getPosX()==Position.ranksToPos.get(Character.toUpperCase(matchingAngle.charAt(0)))){
                                return  figures.get(i).getPosition();
                            }
                            if(Character.isDigit(matchingAngle.charAt(0))&&
                                    figures.get(i).getPosition().getPosY()==Position.filesToPos.get(Character.toUpperCase(matchingAngle.charAt(0)))){
                                return figures.get(i).getPosition();
                            }
                            break;
                        case 2:
                            if(figures.get(i).getPosition().getPosX()==Position.ranksToPos.get(Character.toUpperCase(matchingAngle.charAt(0))) &&
                                    figures.get(i).getPosition().getPosY()==Position.filesToPos.get(matchingAngle.charAt(1))){
                                return figures.get(i).getPosition();
                            }
                            break;
                        default:
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
    public boolean isPositionOccupiedByAlly(Position position, Color color){
        if(isPositionNotOccupied(position)){return false;}

        return getFigureOn(position).getColor() == color;
    }
}