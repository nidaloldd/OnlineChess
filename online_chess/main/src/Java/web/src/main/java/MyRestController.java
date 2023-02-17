package Java.web.src.main.java;

import Java.model.ChessParty;
import Java.model.Player;
import Java.model.Position;
import Java.service.ChessService;
import Java.service.impl.ChessServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/validMoves")
public class MyRestController {

    private final ChessService chessService;
    public MyRestController(final ChessService chessService) {
        this.chessService = chessService;
    }
    private ChessParty chessParty = new ChessParty(new Player("player1"),new Player("player2"));;

    @GetMapping("/{pos}")
    List<String> getRolePlay(final @PathVariable String pos) {
        return chessParty.getTable().getFigureOn(pos).getValidMoves().stream().map(n -> Position.toString(n)).collect(Collectors.toList());
    }
}
