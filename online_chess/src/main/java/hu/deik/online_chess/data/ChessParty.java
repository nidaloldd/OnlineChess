package hu.deik.online_chess.data;

import hu.deik.online_chess.data.Player;
import hu.deik.online_chess.model.GameStatus;
import hu.deik.online_chess.model.Table;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class ChessParty {

    private String id;
    private Table table;
    private Player whitePlayer;
    private  Player blackPlayer;
    private  Player winner;
    private GameStatus status;

    public ChessParty() {
        this.table = new Table();
    }

}
