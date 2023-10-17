package hu.deik.online_chess.manager;

import hu.deik.online_chess.data.ChessParty;

import java.util.HashMap;
import java.util.Map;

public class ChessGameManager {

    private static Map<String, ChessParty> games;
    private static ChessGameManager gameManager;
    private ChessGameManager() {
        games = new HashMap<>();
    }

    public static synchronized ChessGameManager getGameManager() {
        if (gameManager == null) {
            gameManager = new ChessGameManager();
        }
        return gameManager;
    }

    public Map<String, ChessParty> getGames() {
        return games;
    }

    public void setGame(ChessParty game) {
        games.put(game.getId(), game);
    }
}
