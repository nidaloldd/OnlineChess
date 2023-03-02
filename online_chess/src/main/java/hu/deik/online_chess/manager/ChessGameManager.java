package hu.deik.online_chess.manager;

import hu.deik.online_chess.model.ChessParty;
import hu.deik.online_chess.model.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ChessGameManager {

    private static Map<String, ChessParty> games;
    private static ChessGameManager instance;
    private ChessGameManager() {
        games = new HashMap<>();
    }

    public static synchronized ChessGameManager getInstance() {
        if (instance == null) {
            instance = new ChessGameManager();
        }
        return instance;
    }

    public Map<String, ChessParty> getGames() {
        return games;
    }

    public void setGame(ChessParty game) {
        games.put(game.getId(), game);
    }
}
