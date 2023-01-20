package Model;

import Model.Figures.*;

import java.util.ArrayList;
import java.util.List;

public class Table {
    public static final int TABLE_SIZE = 8;
    private Color activePlayerColor = Color.WHITE;
    private King whiteKing , blackKing;
    private final List<Figure> figures = new ArrayList<>();
    private List<Figure> takenFigures = new ArrayList<>();
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
        int WhiteKingNumber = 0;
        int BlackKingNumber = 0;
        for(Figure figure : figures){
            if(figure instanceof King ){
                if(figure.getColor()==Color.WHITE){
                    WhiteKingNumber += 1;
                }
                else {
                    BlackKingNumber += 1;
                }
                if(WhiteKingNumber > 1 || BlackKingNumber>1){ return false;}
            }
        }
        return WhiteKingNumber != 0 && BlackKingNumber != 0;
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

        if(getEnemyValidMoves(color).contains(getKing(color).getPosition())){
            return true;
        }
        else{
            return false;
        }

    }
    public List<Position> getEnemyValidMoves(Color color){
        List<Position> enemyMoves = new ArrayList<>();
        for(Figure figure : figures){
            if(figure.getColor() == Color.getOpposite(color)){
                if(figure instanceof King){
                    for(Direction direction : Direction.getAllDirections()){
                        enemyMoves.addAll(figure.getValidMovesFromOneDirection(direction,"onlyOneStep"));
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

    private void handleCastle(Position moveFrom, Position moveTo) {
        if(!(getFigureOn(moveFrom) instanceof King)){return;}

        if(moveFrom.equals(Position.toPosition("E1"))){
            if(moveTo.equals(Position.toPosition("G1"))){
                //White Small Castle
                getFigureOn(Position.toPosition("H1")).setPosition(Position.toPosition("F1"));
            }
            else if(moveTo.equals(Position.toPosition("C1"))){
                //White Big Castle
                getFigureOn(Position.toPosition("A1")).setPosition(Position.toPosition("D1"));
            }
        }
        else if(moveFrom.equals(Position.toPosition("E8"))){
                if(moveTo.equals(Position.toPosition("G8"))){
                    //Black Small Castle
                    getFigureOn(Position.toPosition("H8")).setPosition(Position.toPosition("F8"));
                }
                else if(moveTo.equals(Position.toPosition("C8"))){
                    //Black Big Castle
                    getFigureOn(Position.toPosition("A8")).setPosition(Position.toPosition("D8"));
                }
        }
    }
    private boolean isSmallCastle(Position moveFrom, Position moveTo,Color color){
        final String row;
        if(color == Color.WHITE){row = "1";}
        else {row = "8";}

        if(moveFrom.equals(Position.toPosition("E"+row))) {
            if (moveTo.equals(Position.toPosition("G"+row))) {
                return true;
            }
        }
        return false;
    }

    private boolean isBigCastle(Position moveFrom, Position moveTo,Color color){
        final String row;
        if(color == Color.WHITE){row = "1";}
        else {row = "8";}

        if(moveFrom.equals(Position.toPosition("E"+row))) {
            if (moveTo.equals(Position.toPosition("B"+row))) {
                return true;
            }
        }
        return false;
    }


    private void takeFigure(Figure figure){
        takenFigures.add(figure);
        figures.remove(figure);
    }

    private void handlePawnPromotion(Position moveFrom, Position moveTo){
        if(!(getFigureOn(moveFrom) instanceof Pawn)){return;}
        var pawn = ((Pawn) getFigureOn(moveFrom));

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
        var pawn = ((Pawn) getFigureOn(moveFrom));

        if(Math.abs(moveFrom.getPosY()-moveTo.getPosY()) == 2){
            var rightNeighbor = getFigureOn(moveTo.stepToDirection(Direction.Right));
            var leftNeighbor = getFigureOn(moveTo.stepToDirection(Direction.Left));
            if(rightNeighbor instanceof Pawn && rightNeighbor.getColor() == Color.getOpposite(activePlayerColor)){
                ((Pawn) rightNeighbor).canDoEntPassantLeft = true;
            }
            if(leftNeighbor instanceof Pawn && rightNeighbor.getColor() == Color.getOpposite(activePlayerColor)){
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
        if(moveTo.isEmpty(this) &&
                (moveFrom.stepToDirection(forwardLeft).equals(moveTo)||moveFrom.stepToDirection(forwardRight).equals(moveTo)) &&
                getFigureOn(moveTo.stepToDirection(back)) instanceof Pawn &&
                getFigureOn(moveTo.stepToDirection(back)).getColor() == Color.getOpposite(activePlayerColor)
        )
        {
           return true;
        }
        return false;
    }
    //1. Nf3 Nf6 2. c4 g6 3. Nc3 Bg7 4. d4 O-O 5. Bf4 d5 6. Qb3 dxc4 7. Qxc4 c6 8. e4 Nbd7 9. Rd1 Nb6 10. Qc5 Bg4 11. Bg5 Na4 12. Qa3 Nxc3 13. bxc3 Nxe4 14. Bxe7 Qb6 15. Bc4 Nxc3 16. Bc5 Rfe8+ 17. Kf1 Be6 18. Bxb6 Bxc4+ 19. Kg1 Ne2+ 20. Kf1 Nxd4+ 21. Kg1 Ne2+ 22. Kf1 Nc3+ 23. Kg1 axb6 24. Qb4 Ra4 25. Qxb6 Nxd1 26. h3 Rxa2 27. Kh2 Nxf2 28. Re1 Rxe1 29. Qd8+ Bf8 30. Nxe1 Bd5 31. Nf3 Ne4 32. Qb8 b5 33. h4 h5 34. Ne5 Kg7 35. Kg1 Bc5+ 36. Kf1 Ng3+ 37. Ke1 Bb4+ 38. Kd1 Bb3+ 39. Kc1 Ne2+ 40. Kb1 Nc3+ 41. Kc1 Rc2# 0-1

    public void  makeMove(String str){
    //   Nf3


    }

    public boolean isPositionOccupied(Position position){
        if(getFigureOn(position)==null){return false;}
        return true;
    }
    public boolean isPositionOccupiedByEnemy(Position position, Color color){
        if(!isPositionOccupied(position)){return false;}

        if(getFigureOn(position).getColor() == Color.getOpposite(color)){return true;}
        return false;
    }

    public void drawTable(){
        System.out.println(makeTableToString());
    }
    public String makeTableToString(Position position) {
        String sol = new String();
        String[][] table = new String[TABLE_SIZE][TABLE_SIZE];
        for (int i = 0; i < TABLE_SIZE; i++) {
            for (int j = 0; j < TABLE_SIZE; j++) {
                table[j][i] = " 00 ";

                for (Figure figure : figures) {
                    if (figure.getPosition().equals(new Position(j, i))) {
                        table[j][i] = figure.toString();
                    }
                }

                for (Position pos : getFigureOn(position).getValidMoves()){
                    if(pos.equals(new Position(j,i))){
                        table[j][i] = " VV ";
                    }
                }

            }
        }
        for (int i = 0; i < TABLE_SIZE; i++) {
            for (int j = 0; j < TABLE_SIZE; j++) {
                sol += table[j][i];
            }
            sol += "\n";
        }
        return sol;
    }
    public String makeTableToString() {
        String sol = new String();
        String[][] table = new String[TABLE_SIZE][TABLE_SIZE];
        for (int i = 0; i < TABLE_SIZE; i++) {
            for (int j = 0; j < TABLE_SIZE; j++) {
                table[j][i] = " 00 ";

                for (Figure figure : figures) {
                    if (figure.getPosition().equals(new Position(j, i))) {
                        table[j][i] = figure.toString();
                    }
                }
            }
        }
        for (int i = 0; i < TABLE_SIZE; i++) {
            for (int j = 0; j < TABLE_SIZE; j++) {
                sol += table[j][i];
            }
            sol += "\n";
        }
        return sol;
    }


}