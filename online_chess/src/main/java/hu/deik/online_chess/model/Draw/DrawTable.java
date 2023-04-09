package hu.deik.online_chess.model.Draw;

import hu.deik.online_chess.model.Color;
import hu.deik.online_chess.model.Position;
import hu.deik.online_chess.model.Table;
import hu.deik.online_chess.model.figures.*;

import java.util.ArrayList;
import java.util.List;

public class DrawTable {

    public static void toConsole(Table table){
        System.out.println(makeTableToString(table));
    }
    public static void toConsoleShowValidMoves(Table table, Position position){
        System.out.println(makeTableToString(table,position));
    }

    private static String[][] createEmptyTable(){
        String[][] tableMatrix = new String[Table.TABLE_SIZE][Table.TABLE_SIZE];
        for (int i = 0; i < Table.TABLE_SIZE; i++) {
            for (int j = 0; j < Table.TABLE_SIZE; j++) {
                tableMatrix[j][i] = " 00 ";
            }
        }
        return tableMatrix;
    }
    private static void FillTableWithFigures(Table table,String[][] tableMatrix){
        for (Figure figure : table.getFigures()) {
            Position pos = figure.getPosition();
            tableMatrix[pos.getPosX()][pos.getPosY()] = figure.toString();
        }
    }
    private static void markValidMovesOnTable(Table table,String[][] tableMatrix,Position position){
        for (Position pos : table.getFigureOn(position).getValidMoves(table)){
            tableMatrix[pos.getPosX()][pos.getPosY()] = " VV ";
        }
    }
    private static String convertMatrixToString(String[][] tableMatrix){
        String sol = "";
        for (int i = 0; i < Table.TABLE_SIZE; i++) {
            for (int j = 0; j < Table.TABLE_SIZE; j++) {
                sol += tableMatrix[j][i];
            }
            sol += "\n";
        }
        return sol;
    }
    public static String makeTableToString(Table table,Position position) {
        String[][] tableMatrix = createEmptyTable();
        FillTableWithFigures(table,tableMatrix);
        markValidMovesOnTable(table,tableMatrix,position);

        return convertMatrixToString(tableMatrix);
    }

    public static String makeTableToString(Table table) {
        String[][] tableMatrix = createEmptyTable();
        FillTableWithFigures(table,tableMatrix);
        return convertMatrixToString(tableMatrix);
    }
    public static List<Figure> makeStringToFigureList(String tableString) {
        List<Figure> figures = new ArrayList<>();
        String[][] board = new String[8][8];
        String[] rows = tableString.split("\n");

        for (int i = 0; i < rows.length; i++) {
            String[] cells = rows[i].split("  ");
            for (int j = 0; j < cells.length; j++) {
                board[j][i] = cells[j];
            }
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++){
                if(board[j][i].equals("00")){continue;}

                Color color;
                if(board[j][i].charAt(0) == 'W'){color = Color.WHITE;}
                else {color = Color.BLACK;}

                Figure figure = switch (board[j][i].charAt(1)) {
                    case 'P' -> new Pawn(color, Position.toString(new Position(j, i)));
                    case 'R' -> new Rook(color, Position.toString(new Position(j, i)));
                    case 'N' -> new Knight(color, Position.toString(new Position(j, i)));
                    case 'B' -> new Bishop(color, Position.toString(new Position(j, i)));
                    case 'K' -> new King(color, Position.toString(new Position(j, i)));
                    case 'Q' -> new Queen(color, Position.toString(new Position(j, i)));
                    default -> null;
                };
                if(figure != null){
                    figures.add(figure);
                }
            }
        }
        return figures;
    }

}
