package Java.service.impl;

import Java.model.ChessParty;
import Java.model.Player;
import Java.model.Position;
import Java.model.Table;
import Java.service.ChessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ChessServiceImpl implements ChessService {

    private ChessParty chessParty;
    @Autowired
    public ChessServiceImpl() {
        chessParty = new ChessParty(new Player("player1"),new Player("player2"));
    }
    @Override
    public Table getTable() {
        return chessParty.getTable();
    }
    @Override
    public List<Position> getValidMoves(Position position) {
        return chessParty.getTable().getFigureOn(position).getValidMoves();
    }
}
