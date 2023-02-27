package hu.deik.online_chess.web;

import hu.deik.online_chess.model.Draw.DrawFigure;
import hu.deik.online_chess.model.Position;
import hu.deik.online_chess.model.Table;
import hu.deik.online_chess.model.figures.Figure;
import hu.deik.online_chess.service.ChessService;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class MyRestController {

    private final ChessService chessService;
    public MyRestController(final ChessService chessService) {
        this.chessService = chessService;
    }
    //private ChessParty chessParty = new ChessParty(new Player("player1"),new Player("player2"));;

    @GetMapping("/validMoves/{pos}")
    List<String> getValidMoves(final @PathVariable String pos) {
        return chessService.getTable().getFigureOn(pos).getValidMoves().stream().map(n -> Position.toString(n)).collect(Collectors.toList());
    }
    @GetMapping("/{pos}")
    List<String> getTable(final @PathVariable String pos) {
        return chessService.getTable().getFigureOn(pos).getValidMoves().stream().map(n -> Position.toString(n)).collect(Collectors.toList());
    }
    @GetMapping("/table")
    public String getTable() {

        Table table = chessService.getTable();
        return chessService.getTableAsJson();
    }
    @GetMapping("/fullTable")
    public Table getFullTable() {
        Table table = chessService.getTable();
        return chessService.getTable();
    }
    @GetMapping("/getImageSource/{notation}")
    public String getImageSource(final @PathVariable String notation) {

        Table table = chessService.getTable();
        return DrawFigure.getPNG(table.getFigureOn(notation));
    }

}
