package hu.deik.online_chess.web;

import hu.deik.online_chess.model.*;
import hu.deik.online_chess.service.ChessPartyService;

import hu.deik.online_chess.service.PlayerService;
import hu.deik.online_chess.service.impl.CustomPlayerDetailsService;
import hu.deik.online_chess.service.impl.PlayerServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Slf4j
@Controller
public class ChessController {
    private final ChessPartyService chessPartyService;
    private PlayerService playerService;
    public ChessController(final ChessPartyService chessPartyService,PlayerService playerService) {
        this.chessPartyService = chessPartyService;
        this.playerService = playerService;
    }
    @GetMapping(path = "/userPage")
    public String userPage(Model model, Authentication authentication){
        log.info("userPage");
        model.addAttribute("userName",authentication.getName());
        model.addAttribute("isAuthenticated",authentication.isAuthenticated());
        model.addAttribute("getAuthorities",authentication.getAuthorities().toString());
        model.addAttribute("getDetails",authentication.getDetails().toString());
        model.addAttribute("getPrincipal",authentication.getPrincipal().toString());
        return "userPage";
    }

    @RequestMapping("/")
    public String index(Model model, Authentication authentication){
        model.addAttribute("getName",authentication.getName());
        model.addAttribute("isAuthenticated",authentication.isAuthenticated());
        return "index";
    }

    @RequestMapping("/game")
    public String game(Model model, Authentication authentication){
        log.info("game page.");
        model.addAttribute("getName",authentication.getName());
        model.addAttribute("isAuthenticated",authentication.isAuthenticated());
        return "chessGame";
    }

    @RequestMapping("/stories")
    public String stories(Model model, Authentication authentication){
        model.addAttribute("getName",authentication.getName());
        model.addAttribute("isAuthenticated",authentication.isAuthenticated());
        return "stories";
    }

    @RequestMapping("/puzzle")
    public String puzzle(Model model, Authentication authentication){
        model.addAttribute("getName",authentication.getName());
        model.addAttribute("isAuthenticated",authentication.isAuthenticated());
        return "puzzle";
    }
    @RequestMapping("/registration")
    public String registration(Model model){
        model.addAttribute("player", new Player());
        return "registration";
    }
    @PostMapping("/reg")
    public String reg(@ModelAttribute Player player) {
        log.info("Uj user!");
//		emailService.sendMessage(user.getEmail());
        log.info(player.getUsername());
        log.info(player.getPassword());
        playerService.registerUser(player);
        return "auth/login";
    }

}
