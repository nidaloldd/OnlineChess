package model;

public class ChessParty {
    Table table;
    private final Player whitePlayer;
    private final Player blackPlayer;
    public Table getTable() {
        return table;
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
