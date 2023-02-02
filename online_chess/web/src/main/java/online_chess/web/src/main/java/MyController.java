package online_chess.web.src.main.java;

import model.*;
import model.Draw.DrawFigure;
import model.figures.Figure;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.Console;

@Controller
public class MyController {
    private  final ChessParty chessParty = new ChessParty(new Player("player1"),new Player("player2"));

    @GetMapping("/index")
    public String user(Model model){
        Table table = chessParty.getTable();

        for (Figure figure : table.getFigures()){
            model.addAttribute(Position.toString(figure.getPosition()), DrawFigure.getPNGImageTag(figure));
        }
        return "index";
    }

    @GetMapping("/update")
    public String user(Model model  ,@RequestParam(name = "inputField", required = false) String inputValue){
        Table table = chessParty.getTable();

        model.addAttribute("inputValue", inputValue);
        table.makeMove(inputValue);
        for (Figure figure : table.getFigures()){
            model.addAttribute(Position.toString(figure.getPosition()),DrawFigure.getPNGImageTag(figure));
        }
        return "index";
    }
}
