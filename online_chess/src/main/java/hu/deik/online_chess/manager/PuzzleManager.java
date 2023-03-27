package hu.deik.online_chess.manager;

import hu.deik.online_chess.data.ChessParty;
import hu.deik.online_chess.data.ChessPuzzle;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class PuzzleManager {
    private static Map<Integer, ChessPuzzle> puzzle;
    private static PuzzleManager instance;
    private PuzzleManager() {
        puzzle = new HashMap<>();
    }

    public static synchronized ChessPuzzle getRandomPuzzle() {
        Random random = new Random();
        return puzzle.get(random.nextInt(puzzle.size()));
    }
    public Map<Integer, ChessPuzzle> getPuzzles() {
        return puzzle;
    }
}
