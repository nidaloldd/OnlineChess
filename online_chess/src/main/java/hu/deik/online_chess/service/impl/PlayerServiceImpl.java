package hu.deik.online_chess.service.impl;

import hu.deik.online_chess.model.Player;
import hu.deik.online_chess.repo.PlayerRepository;
import hu.deik.online_chess.service.PlayerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;
@Slf4j
@Service
public class PlayerServiceImpl implements PlayerService {
    private PlayerRepository playerRepository;
    private final String USER_ROLE = "USER";
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public PlayerServiceImpl(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Override
    public Player findByUsername(String name){
        return playerRepository.findByUsername(name);
    }
    @Override
    public String registerUser(Player playerToRegister){
        Player userCheck = playerRepository.findByUsername(playerToRegister.getUsername());

        if (userCheck != null)
            return "alreadyExists";

        playerToRegister.setPassword(bCryptPasswordEncoder.encode(playerToRegister.getPassword()));
        playerToRegister.setRole(USER_ROLE);
        playerToRegister.setEnabled(false);
        playerToRegister.setActivation(generateKey());

        log.info(playerToRegister.getPassword());
        playerRepository.save(playerToRegister);

        return "ok";
    }
    @Override
    public String generateKey()
    {
        String key = "";
        Random random = new Random();
        char[] word = new char[16];
        for (int j = 0; j < word.length; j++) {
            word[j] = (char) ('a' + random.nextInt(26));
        }
        String toReturn = new String(word);
        log.debug("random code: " + toReturn);
        return new String(word);
    }

    @Override
    public String userActivation(String code) {
        Player player = playerRepository.findByActivation(code);
        if (player == null){
            return "noresult";
        }

        player.setEnabled(true);
        player.setActivation("");
        playerRepository.save(player);
        return "ok";
    }
}