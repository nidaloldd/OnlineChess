package hu.deik.online_chess.web;

import hu.deik.online_chess.data.ChessParty;
import hu.deik.online_chess.data.ChessPuzzle;
import hu.deik.online_chess.data.Player;
import hu.deik.online_chess.exeption.InvalidGameException;
import hu.deik.online_chess.exeption.InvalidParamException;
import hu.deik.online_chess.exeption.NotFoundException;
import hu.deik.online_chess.model.CustomPlayerDetails;
import hu.deik.online_chess.model.Draw.DrawTable;
import hu.deik.online_chess.model.GameStatus;
import hu.deik.online_chess.model.Position;
import hu.deik.online_chess.model.Table;
import hu.deik.online_chess.repo.PlayerRepository;
import hu.deik.online_chess.repo.PuzzleRepository;
import hu.deik.online_chess.service.ChessPartyService;
import hu.deik.online_chess.service.dto.PuzzleRequest;
import hu.deik.online_chess.service.dto.ScoreRowRequest;
import hu.deik.online_chess.service.impl.CustomPlayerDetailsService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/game")
@Slf4j
@AllArgsConstructor
public class ChessRestController {
    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    private PuzzleRepository puzzleRepository;
    @Autowired
    private CustomPlayerDetailsService playerDetailsService;
    @Autowired
    private final SimpMessagingTemplate simpMessagingTemplate;

    private final ChessPartyService chessPartyService;


    @PostMapping("/start")
    public ResponseEntity<ChessParty> start(Authentication authentication) {
        Player player1 = getPlayer(authentication);
        log.info("start game request: {}", player1.getUsername());
        return ResponseEntity.ok(chessPartyService.createGame(player1,GameStatus.NEW));
    }
    @PostMapping("/connect")
    public ResponseEntity<ChessParty> connect(@RequestBody String gameId,Authentication authentication) throws InvalidParamException, InvalidGameException {
        log.info("connect request: {}");
        return ResponseEntity.ok(chessPartyService.connectToGame(getPlayer(authentication), gameId));
    }

    @PostMapping("/connectRandom")
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
        return playerRepository.findByUsername(username);
    }

    @GetMapping("/getRandomPuzzle")
    public ResponseEntity<PuzzleRequest> getRandomPuzzle(Authentication authentication)  {
        log.info("getRandomPuzzle");
        Random random = new Random();
        int count = (int) puzzleRepository.count();
        int randomIndex = random.nextInt(count);
        Pageable pageable = PageRequest.of(randomIndex, 1);

        Player player = playerRepository.findByUsername(authentication.getName());
        ChessParty game = chessPartyService.createGame(player,GameStatus.IN_PROGRESS);

        ChessPuzzle puzzle =  puzzleRepository.findAll(pageable).getContent().get(0);

        log.info("puzzle.getId() {}",puzzle.getId());
        Table table = new Table(DrawTable.makeStringToFigureList(puzzle.getTableString()),puzzle.getColor());
        game.setTable(table);

        log.info(puzzle.getTableString());


        return ResponseEntity.ok(new PuzzleRequest(game,puzzle));
    }

    @GetMapping("/getPuzzle/{puzzleId}")
    public ResponseEntity<PuzzleRequest> getPuzzle(final @PathVariable Long puzzleId,Authentication authentication)  {
        log.info("getPuzzle");

        Player player = playerRepository.findByUsername(authentication.getName());
        ChessParty game = chessPartyService.createGame(player,GameStatus.IN_PROGRESS);

        ChessPuzzle puzzle =  puzzleRepository.findById(puzzleId).get();

        log.info("puzzle.getId() {}",puzzle.getId());
        Table table = new Table(DrawTable.makeStringToFigureList(puzzle.getTableString()),puzzle.getColor());
        game.setTable(table);

        log.info(puzzle.getTableString());

        return ResponseEntity.ok(new PuzzleRequest(game,puzzle));
    }

    @GetMapping("/getLocalGame")
    public ResponseEntity<ChessParty> getLocalGame(Authentication authentication)  {
        log.info("getLocalGame");
        Player player = playerRepository.findByUsername(authentication.getName());
        ChessParty game = chessPartyService.createGame(player,GameStatus.IN_PROGRESS);
        return ResponseEntity.ok(game);
    }

    @GetMapping("/makeMove/{gameId}/{from}/{to}")
    public ResponseEntity<ChessParty> makeMove(final @PathVariable String gameId,
                                               final @PathVariable String from,
                                               final @PathVariable String to)
            throws NotFoundException, InvalidGameException   {
        log.info("makeMove");

        ChessParty game = chessPartyService.makeMove(gameId,from,to);
        if(game.getTable().isGameOver()){
            game.setStatus(GameStatus.FINISHED);
        }

        simpMessagingTemplate.convertAndSend("/topic/game-progress/" + game.getId(), game);

        return ResponseEntity.ok(game);
    }
    @GetMapping("/validMoves/{gameId}/{position}")
    public List<String> getValidMoves(final @PathVariable String gameId,final @PathVariable String position) {
        log.info("getValidMoves");
        var table = chessPartyService.getTable(gameId);
        return table.getFigureOn(position).getValidMoves(table).stream().map(n -> Position.toString(n)).collect(Collectors.toList());
    }

    @PostMapping("/scoreTable")
    public ResponseEntity<List<ScoreRowRequest>> makeMove() {
        List<ScoreRowRequest> scoreTable = new ArrayList<>();
        var players = playerRepository.findAllByOrderByScoreDesc();

        for(Player player : players){
            scoreTable.add(new ScoreRowRequest(player.getUsername(), player.getEmail(),player.getScore()));
        }

        return ResponseEntity.ok(scoreTable);
    }

}
