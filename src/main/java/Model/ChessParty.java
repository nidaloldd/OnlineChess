package Model;

public class ChessParty {

    Player whitePlayer;
    Player blackPlayer;
    Table table;
    Player activePlayer;

    public Table getTable() {
        return table;
    }
    public void switchActivPlayer(){
        if(activePlayer.equals(whitePlayer)){
            activePlayer = blackPlayer;
        }
        else {
            activePlayer = whitePlayer;
        }
    }
    public ChessParty(Player whitePlayer, Player blackPlayer) {
        this.whitePlayer = whitePlayer;
        this.blackPlayer = blackPlayer;
        activePlayer = whitePlayer;
        this.table = new Table();
    }

}
