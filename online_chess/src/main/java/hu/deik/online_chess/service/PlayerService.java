package hu.deik.online_chess.service;

import hu.deik.online_chess.data.Player;


public interface PlayerService {
    public Player findByUsername(String name);
    public String registerUser(Player player);
    public String userActivation(String code) ;
    public String generateKey();

}
