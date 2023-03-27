package hu.deik.online_chess.service.dto;

import hu.deik.online_chess.data.ChessParty;
import hu.deik.online_chess.data.ChessPuzzle;
import lombok.Data;

@Data
public class PuzzleRequest {
    private ChessParty chessParty;
    private ChessPuzzle chessPuzzle;

    public PuzzleRequest(ChessParty game, ChessPuzzle puzzle) {
        chessParty = game;
        chessPuzzle = puzzle;
    }
}
