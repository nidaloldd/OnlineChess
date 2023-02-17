package Java.web.src.main.java;

import Java.model.ChessParty;
import Java.model.*;
import Java.model.Draw.DrawFigure;
import Java.model.figures.Figure;
import Java.service.ChessService;
import Java.service.impl.ChessServiceImpl;
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
        Table table = chessService.getTable();

        for (Figure figure : table.getFigures()){
            model.addAttribute(Position.toString(figure.getPosition()), DrawFigure.getPNGImageTag(figure));
        }
        return "index";
    }

    @GetMapping("/update")
    public String updateByInput(Model model  ,@RequestParam(name = "inputField", required = false) String inputValue){
        Table table = chessService.getTable();

        model.addAttribute("inputValue", inputValue);
        table.makeMove(inputValue);
        for (Figure figure : table.getFigures()){
            model.addAttribute(Position.toString(figure.getPosition()), DrawFigure.getPNGImageTag(figure));
        }
        return "index";
    }

    @GetMapping("/update/{notation}")
    public String updateByNotation(Model model  , final @PathVariable("notation") String notation) {
        Table table = chessService.getTable();

        table.makeMove(notation);
        for (Figure figure : table.getFigures()){
            model.addAttribute(Position.toString(figure.getPosition()), DrawFigure.getPNGImageTag(figure));
        }
        return "index";
    }
    @GetMapping("/update/{stepFrom}/{stepTo}")
    public String updateByIndex(Model model  , final @PathVariable("stepFrom") String stepFrom, final @PathVariable("stepTo") String stepTo) {
        Table table = chessService.getTable();

        table.makeMove(Position.toPosition(stepFrom),Position.toPosition(stepTo));
        for (Figure figure : table.getFigures()){
            model.addAttribute(Position.toString(figure.getPosition()), DrawFigure.getPNGImageTag(figure));
        }
        return "index";
    }
    @GetMapping("/update/validMoves/{index}")
    public String updateByIndex(Model model, final @PathVariable("index") String index) {
        Table table = chessService.getTable();

        var validMoves = table.getFigureOn(index).getValidMoves();
        for (Position position : validMoves){
            model.addAttribute(Position.toString(position),"K");
        }
        for (Figure figure : table.getFigures()){
            model.addAttribute(Position.toString(figure.getPosition()), DrawFigure.getPNGImageTag(figure));
        }
        return "index";
    }


}
