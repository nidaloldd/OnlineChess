package hu.deik.online_chess.web;

import hu.deik.online_chess.model.Draw.DrawFigure;
import hu.deik.online_chess.model.*;
import hu.deik.online_chess.model.figures.Figure;
import hu.deik.online_chess.service.ChessService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class MyController {
    private final ChessService chessService;
    public MyController(final ChessService chessService) {
        this.chessService = chessService;
    }
    @GetMapping
    public String getIndex(Model model){

        return "index";
    }
    @GetMapping("/updateTable/{stepFrom}/{stepTo}")
    public String makeMove(Model model  , final @PathVariable("stepFrom") String stepFrom, final @PathVariable("stepTo") String stepTo) {
        Table table = chessService.getTable();
        table.makeMove(Position.toPosition(stepFrom),Position.toPosition(stepTo));
        return "index";
    }

}
