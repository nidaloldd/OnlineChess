package hu.deik.online_chess.model;


import hu.deik.online_chess.model.Draw.DrawFigure;
import hu.deik.online_chess.model.figures.Figure;

public class ChessParty {
    private Table table;
    private final Player whitePlayer;
    private final Player blackPlayer;
    public Table getTable() {
        return table;
    }
    public String getTableAsJson(){
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        for (Figure figure: table.getFigures()) {
            sb.append("\"");
            sb.append(Position.toString(figure.getPosition()));
            sb.append("\"");
            sb.append(":");
            sb.append("\"");
            sb.append(DrawFigure.getPNG(figure));
            sb.append("\"");

            sb.append(",");
        }
        sb.append("}");
        sb.deleteCharAt(sb.lastIndexOf(","));
        return sb.toString();
    }

    public ChessParty(Player whitePlayer, Player blackPlayer) {
        this.whitePlayer = whitePlayer;
        this.blackPlayer = blackPlayer;
        this.table = new Table();
    }
    public Player getActivePlayer(){
        if(table.getActivePlayerColor() == Color.WHITE){
            return whitePlayer;
        }
        else {
            return  blackPlayer;
        }
    }

}
