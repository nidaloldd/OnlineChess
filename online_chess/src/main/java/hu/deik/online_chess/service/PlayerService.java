package hu.deik.online_chess.service;

import hu.deik.online_chess.data.Player;
import hu.deik.online_chess.model.GameResult;


public interface PlayerService {
    public Player findByUsername(String name);
    public String registerUser(Player player);
    public String userActivation(String code) ;
    public String generateKey();
    public double absoluteRatingChange(double Ra , double Rb, GameResult result ) ;
}
