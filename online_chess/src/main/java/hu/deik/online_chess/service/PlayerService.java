package hu.deik.online_chess.service;

import hu.deik.online_chess.model.Player;
import org.springframework.stereotype.Service;


public interface PlayerService {
    public Player findByUsername(String name);
    public String registerUser(Player player);
    public String userActivation(String code) ;

}
