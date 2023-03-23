package hu.deik.online_chess.web;

import hu.deik.online_chess.model.*;
import hu.deik.online_chess.repo.PlayerRepository;
import hu.deik.online_chess.service.ChessPartyService;

import hu.deik.online_chess.service.PlayerService;
import hu.deik.online_chess.service.impl.CustomPlayerDetailsService;
import hu.deik.online_chess.service.impl.PlayerServiceImpl;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import hu.deik.online_chess.service.impl.EmailService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Slf4j
@Controller
public class ChessController {
    private final ChessPartyService chessPartyService;
    private PlayerService playerService;
    private EmailService emailService;
    private PlayerRepository playerRepository;
    public ChessController(final ChessPartyService chessPartyService, PlayerService playerService, EmailService emailService, PlayerRepository playerRepository) {
        this.chessPartyService = chessPartyService;
        this.playerService = playerService;
        this.emailService = emailService;
        this.playerRepository = playerRepository;
    }


    @RequestMapping("/")
    public String index(Model model, Authentication authentication){
        model.addAttribute("getName",authentication.getName());
        model.addAttribute("isAuthenticated",authentication.isAuthenticated());
        return "index";
    }
    @RequestMapping("/userPage")
    public String userPage(Model model, Authentication authentication){
        log.info("userPage");
        model.addAttribute("activationError",false);
        setUserPage(model,authentication);
        return "userPage";
    }

    public void setUserPage(Model model, Authentication authentication){
        model.addAttribute("isAuthenticated",authentication.isAuthenticated());
        Player player = playerRepository.findByUsername(authentication.getName());

        model.addAttribute("getName",player.getUsername());
        model.addAttribute("getEmail",player.getEmail());
        model.addAttribute("getActivation",player.getActivation());
        model.addAttribute("getEnabled",player.getEnabled());
        model.addAttribute("sendEmailError",false);
        model.addAttribute("activationError",false);

        model.addAttribute("sendEmailSuccess",false);
        model.addAttribute("activationSuccess",false);
    }
    @RequestMapping("/game")
    public String game(Model model, Authentication authentication){
        Player player = playerRepository.findByUsername(authentication.getName());
        log.info("game page.");
        model.addAttribute("getName",authentication.getName());
        model.addAttribute("isAuthenticated",authentication.isAuthenticated());
        model.addAttribute("getEnabled",player.getEnabled());
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

        log.info("new user!");

        log.info(player.getUsername());
        log.info(player.getPassword());
        log.info(player.getEmail());

        playerService.registerUser(player);
        emailService.sendMessage(player.getUsername());

        return "auth/login";
    }

    @PostMapping("/activation")
    public String activation(@RequestParam("activationCode") String activationCode,Model model, Authentication authentication) {
        String result = playerService.userActivation(activationCode);
        setUserPage(model,authentication);
        if(result.equals("ok")){
            model.addAttribute("activationSuccess",true);
            return "userPage";
        }
        model.addAttribute("activationError",true);
        return "userPage";
    }
    @PostMapping("/resendEmail/{username}")
    public String resendEmail(@PathVariable String username,Model model, Authentication authentication) {
        log.info("resendEmail");
        log.info(username);
        try {
            Player player = playerRepository.findByUsername(username);
            player.setActivation(playerService.generateKey());
            playerRepository.save(player);
            emailService.sendMessage(username);
            setUserPage(model,authentication);
            setUserPage(model,authentication);
            model.addAttribute("sendEmailSuccess",true);
            return "userPage";
        }catch (Exception e){
            setUserPage(model,authentication);
            model.addAttribute("sendEmailError",true);
            return "userPage";
        }


    }

}
