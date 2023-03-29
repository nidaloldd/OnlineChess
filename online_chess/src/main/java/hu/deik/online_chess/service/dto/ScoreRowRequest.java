package hu.deik.online_chess.service.dto;

import hu.deik.online_chess.data.ChessParty;
import hu.deik.online_chess.data.ChessPuzzle;
import lombok.Data;

@Data
public class ScoreRowRequest {
    private String username;
    private String email;
    private double score;

    public ScoreRowRequest(String username, String email, double score) {
        this.username = username;
        this.email = email;
        this.score = score;
    }
}
