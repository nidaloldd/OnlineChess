package hu.deik.online_chess.model;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class ChessParty {

    private String id;
    private Table table;
    private  Player whitePlayer;
    private  Player blackPlayer;
    private  Player winner;
    private GameStatus status;

    public ChessParty() {
        this.table = new Table();
    }

}
