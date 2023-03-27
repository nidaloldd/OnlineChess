package hu.deik.online_chess.service.impl;

import hu.deik.online_chess.model.CustomPlayerDetails;
import hu.deik.online_chess.data.Player;
import hu.deik.online_chess.repo.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsService")
public class CustomPlayerDetailsService implements UserDetailsService {

    @Autowired
    private PlayerRepository playerRepository;

    @Override
    public UserDetails loadUserByUsername(String playerName) throws UsernameNotFoundException {

        Player player = playerRepository.findByUsername(playerName);
        if(player ==null) {
            throw new UsernameNotFoundException("User Not Found");
        }
        return new CustomPlayerDetails(player);
    }

}
