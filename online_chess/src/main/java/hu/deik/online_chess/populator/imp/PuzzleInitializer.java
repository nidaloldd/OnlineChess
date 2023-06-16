package hu.deik.online_chess.populator.imp;

import hu.deik.online_chess.data.ChessPuzzle;
import hu.deik.online_chess.model.Color;
import hu.deik.online_chess.populator.DBPopulator;
import hu.deik.online_chess.repo.PuzzleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class PuzzleInitializer implements DBPopulator {
    private final PuzzleRepository puzzleRepository;

    public PuzzleInitializer(final PuzzleRepository puzzleRepository) {
        this.puzzleRepository = puzzleRepository;
    }

    @Override
    public void populateDatabase() {
        puzzleRepository.deleteAll(puzzleRepository.findAll());
        log.info("Initialize Puzzle...");
        puzzleRepository.saveAll(PUZZLES);
        log.info("Finished initialization of Puzzle.");
    }

    private static final List<ChessPuzzle> PUZZLES = List.of(
            new ChessPuzzle(
                    """
                            BR  BK  BR  00  00  00  00  00
                            BP  BN  BP  00  00  00  00  00
                            00  00  00  00  00  00  00  00
                            00  00  00  00  WN  00  00  00
                            00  WP  WP  BP  00  00  00  00
                            00  00  00  00  00  00  00  00
                            00  00  00  00  WK  00  00  00
                            00  00  00  00  00  00  00  00
                            """
                    ,Color.WHITE,"E5-C6",""),
            new ChessPuzzle(
                    """
                            00  00  00  00  00  BB  BK  00
                            BR  00  00  00  00  00  BP  BN
                            BN  00  00  00  00  BP  00  00
                            00  00  BP  00  WN  00  00  BP
                            00  00  00  00  00  00  00  WP
                            WP  BP  00  00  WB  00  WP  00
                            00  WK  00  00  WB  WP  00  00
                            00  00  00  00  00  00  00  00
                            """
                    ,Color.WHITE,"E2-C4,E5-G6","G8-H8"),
            new ChessPuzzle(
                    """
                            BK  00  00  00  00  00  00  BR
                            BP  00  00  WR  00  00  00  00
                            WP  00  00  00  00  00  00  00
                            00  WR  00  00  00  00  00  00
                            00  WN  00  00  BP  WP  00  00
                            00  BP  00  00  WP  BB  00  00
                            00  00  00  00  BR  00  00  00
                            00  00  00  00  00  WK  00  00
                            """
                    ,Color.WHITE,"B5-H5,B4-C6,D7-A7","F3-H5,H5-F3")
    );
}
