package hu.deik.online_chess.web;

import hu.deik.online_chess.exeption.InvalidGameException;
import hu.deik.online_chess.exeption.InvalidParamException;
import hu.deik.online_chess.exeption.NotFoundException;
import hu.deik.online_chess.model.ChessParty;
import hu.deik.online_chess.model.Player;
import hu.deik.online_chess.model.Position;
import hu.deik.online_chess.model.Table;
import hu.deik.online_chess.service.ChessPartyService;
import hu.deik.online_chess.service.dto.MoveRequest;
import lombok.AllArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import hu.deik.online_chess.service.dto.ConnectRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@Slf4j
@AllArgsConstructor
public class ChessRestController {

    private final ChessPartyService chessPartyService;
    private final SimpMessagingTemplate simpMessagingTemplate;

    @PostMapping("/start")
    public ResponseEntity<ChessParty> start(@RequestBody String playerName) {
        log.info("start game request: {}", playerName);
        Player player1 = new Player(playerName);
        return ResponseEntity.ok(chessPartyService.createGame(player1));
    }
    @PostMapping("/connect")
    public ResponseEntity<ChessParty> connect(@RequestBody ConnectRequest request) throws InvalidParamException, InvalidGameException {
        log.info("connect request: {}", request);
        return ResponseEntity.ok(chessPartyService.connectToGame(request.getPlayer(), request.getGameId()));
    }
    @PostMapping("/connect/random")
    public ResponseEntity<ChessParty> connectRandom(@RequestBody String playerName) throws NotFoundException {
        log.info("connect random {}", playerName);
        Player player2 = new Player(playerName);
        return ResponseEntity.ok(chessPartyService.connectToRandomGame(player2));
    }

    @GetMapping("/validMoves/{gameId}/{pos}")

    List<String> getValidMoves(final @PathVariable String gameId,final @PathVariable String pos) {
        log.info("getValidMoves");
        return chessPartyService.getTable(gameId).getFigureOn(pos).getValidMoves().stream().map(n -> Position.toString(n)).collect(Collectors.toList());
    }


    @PostMapping("/makeMove")
    public ResponseEntity<ChessParty> makeMove(@RequestBody MoveRequest request) throws NotFoundException, InvalidGameException  {
        log.info("makeMove");
        ChessParty game = chessPartyService.makeMove(request);
        simpMessagingTemplate.convertAndSend("/topic/game-progress/" + game.getId(), game);
        return ResponseEntity.ok(game);
    }

}
