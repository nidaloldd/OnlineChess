package hu.deik.online_chess.model.Draw;

import hu.deik.online_chess.model.Table;
import hu.deik.online_chess.model.Position;
import hu.deik.online_chess.model.figures.Figure;

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
}
