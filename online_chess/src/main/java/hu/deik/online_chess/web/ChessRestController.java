package hu.deik.online_chess.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import hu.deik.online_chess.exeption.InvalidGameException;
import hu.deik.online_chess.exeption.InvalidParamException;
import hu.deik.online_chess.exeption.NotFoundException;
import hu.deik.online_chess.model.*;
import hu.deik.online_chess.repo.PlayerRepository;
import hu.deik.online_chess.service.ChessPartyService;
import hu.deik.online_chess.service.dto.MoveRequest;
import hu.deik.online_chess.service.impl.CustomPlayerDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/game")
@Slf4j
@AllArgsConstructor
public class ChessRestController {
    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    private CustomPlayerDetailsService playerDetailsService;

    private final ChessPartyService chessPartyService;
    private final SimpMessagingTemplate simpMessagingTemplate;

    @PostMapping("/start")
    public ResponseEntity<ChessParty> start(Authentication authentication) {
        Player player1 = getPlayer(authentication);
        log.info("start game request: {}", player1.getUsername());
        return ResponseEntity.ok(chessPartyService.createGame(player1));
    }
    @PostMapping("/connect")
    public ResponseEntity<ChessParty> connect(@RequestBody String gameId,Authentication authentication) throws InvalidParamException, InvalidGameException {
        log.info("connect request: {}");
        return ResponseEntity.ok(chessPartyService.connectToGame(getPlayer(authentication), gameId));
    }

    @PostMapping("/connect/random")
    public ResponseEntity<ChessParty> connectRandom(Authentication authentication) throws NotFoundException {
        Player player2 = getPlayer(authentication);
        log.info("connectRandom game request: {}", player2.getUsername());
        return ResponseEntity.ok(chessPartyService.connectToRandomGame(player2));
    }
    @PostMapping("/getPlayer")
    public ResponseEntity<Player> getPlayerResponse(Authentication authentication) {
        return ResponseEntity.ok(getPlayer(authentication));
    }
    public Player getPlayer(Authentication authentication){
        String username = authentication.getName();
        CustomPlayerDetails playerDetails = (CustomPlayerDetails) playerDetailsService.loadUserByUsername(username);
        return playerDetails.getPlayer();
    }

    @GetMapping("/validMoves/{gameId}/{pos}")

    List<String> getValidMoves(final @PathVariable String gameId,final @PathVariable String pos) {
        log.info("getValidMoves");
        var table = chessPartyService.getTable(gameId);
        return table.getFigureOn(pos).getValidMoves(table).stream().map(n -> Position.toString(n)).collect(Collectors.toList());
    }


    @PostMapping("/makeMove")
    public ResponseEntity<ChessParty> makeMove(@RequestBody MoveRequest request) throws NotFoundException, InvalidGameException  {
        log.info("makeMove");
        ChessParty game = chessPartyService.makeMove(request);
        simpMessagingTemplate.convertAndSend("/topic/game-progress/" + game.getId(), game);
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            log.info("gameOBJEKT:{}",String.valueOf(objectMapper.writeValueAsString(game)));
        }
        catch (Exception e){
            log.info(e.toString());
        }
        log.info("gameOBJEKT:{}",String.valueOf(game));

        return ResponseEntity.ok(game);
    }

}
