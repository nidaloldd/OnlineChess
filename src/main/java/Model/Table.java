package Model;

import Model.Figures.*;

import java.util.ArrayList;
import java.util.List;

public class Table {
    public static int tableSize = 8;
    Color activePlayerColor;
    private King whiteKing , blackKing;
    private List<Figure> figures = new ArrayList<>();
    private List<Figure> takenFigures = new ArrayList<>();
    public List<Figure> getFigures() {
        return figures;
    }

    public King getKing(Color color){
        if(color == Color.White){
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
        for (Figure f : figures){
            if(position.equals(f.getPosition())){
                return f;
            }
        }
        return null;
    }
    public Table(){
        setUpStartingTable();
    }
    private void setUpStartingTable(){
        figures.clear();
        activePlayerColor = Color.White;
        whiteKing = (new King(this,Color.White,"E1"));
        blackKing = (new King(this,Color.Black,"E8"));

        figures.add((new Rook(this, Color.White,"A1")));
        figures.add((new Knight(this,Color.White,"B1")));
        figures.add((new Bishop(this,Color.White,"C1")));
        figures.add((new Queen(this,Color.White,"D1")));
        figures.add(whiteKing);
        figures.add((new Bishop(this,Color.White,"F1")));
        figures.add((new Knight(this,Color.White,"G1")));
        figures.add((new Rook(this,Color.White,"H1")));

        figures.add((new Pawn(this,Color.White,"A2")));
        figures.add((new Pawn(this,Color.White,"B2")));
        figures.add((new Pawn(this,Color.White,"C2")));
        figures.add((new Pawn(this,Color.White,"D2")));
        figures.add((new Pawn(this,Color.White,"E2")));
        figures.add((new Pawn(this,Color.White,"F2")));
        figures.add((new Pawn(this,Color.White,"G2")));
        figures.add((new Pawn(this,Color.White,"H2")));

        figures.add((new Rook(this,Color.Black,"A8")));
        figures.add((new Knight(this,Color.Black,"B8")));
        figures.add((new Bishop(this,Color.Black,"C8")));
        figures.add((new Queen(this,Color.Black,"D8")));
        figures.add(blackKing);
        figures.add((new Bishop(this,Color.Black,"F8")));
        figures.add((new Knight(this,Color.Black,"G8")));
        figures.add((new Rook(this,Color.Black,"H8")));

        figures.add((new Pawn(this,Color.Black,"A7")));
        figures.add((new Pawn(this,Color.Black,"B7")));
        figures.add((new Pawn(this,Color.Black,"C7")));
        figures.add((new Pawn(this,Color.Black,"D7")));
        figures.add((new Pawn(this,Color.Black,"E7")));
        figures.add((new Pawn(this,Color.Black,"F7")));
        figures.add((new Pawn(this,Color.Black,"G7")));
        figures.add((new Pawn(this,Color.Black,"H7")));
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

    public void  makeMove(Position moveFrom, Position moveTo){

        if(getFigureOn(moveFrom)==null){System.out.println("There is no Figure on Position "+ Position.toString(moveFrom)); return; }
        if(getFigureOn(moveFrom).getColor() != activePlayerColor){System.out.println("Other Player Turn");return;}

        List<Position> validMoves = getFigureOn(moveFrom).getValidMoves();
        if(!validMoves.contains(moveTo)){System.out.println(moveTo.toString()+" is Not Valid move"); return;}

        takenFigures.add(getFigureOn(moveTo));
        figures.remove(getFigureOn(moveTo));
        getFigureOn(moveFrom).setPosition(moveTo);

        //drawTable();
        activePlayerColor = Color.getOpposite(activePlayerColor);
        //isKingInCheck(activePlayerColor);
    }
    //1. Nf3 Nf6 2. c4 g6 3. Nc3 Bg7 4. d4 O-O 5. Bf4 d5 6. Qb3 dxc4 7. Qxc4 c6 8. e4 Nbd7 9. Rd1 Nb6 10. Qc5 Bg4 11. Bg5 Na4 12. Qa3 Nxc3 13. bxc3 Nxe4 14. Bxe7 Qb6 15. Bc4 Nxc3 16. Bc5 Rfe8+ 17. Kf1 Be6 18. Bxb6 Bxc4+ 19. Kg1 Ne2+ 20. Kf1 Nxd4+ 21. Kg1 Ne2+ 22. Kf1 Nc3+ 23. Kg1 axb6 24. Qb4 Ra4 25. Qxb6 Nxd1 26. h3 Rxa2 27. Kh2 Nxf2 28. Re1 Rxe1 29. Qd8+ Bf8 30. Nxe1 Bd5 31. Nf3 Ne4 32. Qb8 b5 33. h4 h5 34. Ne5 Kg7 35. Kg1 Bc5+ 36. Kf1 Ng3+ 37. Ke1 Bb4+ 38. Kd1 Bb3+ 39. Kc1 Ne2+ 40. Kb1 Nc3+ 41. Kc1 Rc2# 0-1

    public void  makeMove(String str){
    //   Nf3


    }
    public void drawTable(){
        System.out.println(makeTableToString());
    }
    public String makeTableToString(Position position) {
        String sol = new String();
        String[][] table = new String[tableSize][tableSize];
        for (int i = 0; i < tableSize; i++) {
            for (int j = 0; j < tableSize; j++) {
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
        for (int i = 0; i < tableSize; i++) {
            for (int j = 0; j < tableSize; j++) {
                sol += table[j][i];
            }
            sol += "\n";
        }
        return sol;
    }
    public String makeTableToString() {
        String sol = new String();
        String[][] table = new String[tableSize][tableSize];
        for (int i = 0; i < tableSize; i++) {
            for (int j = 0; j < tableSize; j++) {
                table[j][i] = " 00 ";

                for (Figure figure : figures) {
                    if (figure.getPosition().equals(new Position(j, i))) {
                        table[j][i] = figure.toString();
                    }
                }
            }
        }
        for (int i = 0; i < tableSize; i++) {
            for (int j = 0; j < tableSize; j++) {
                sol += table[j][i];
            }
            sol += "\n";
        }
        return sol;
    }


}