package hu.deik.online_chess.service.impl;

import hu.deik.online_chess.data.Player;
import hu.deik.online_chess.repo.PlayerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmailService {
    @Value("${spring.mail.username}")
    private String MESSAGE_FROM;

    private JavaMailSender javaMailSender;
    private PlayerRepository playerRepository;
    @Autowired
    public void EmailService(JavaMailSender javaMailSender,PlayerRepository playerRepository) {
        this.javaMailSender = javaMailSender;
        this.playerRepository = playerRepository;
    }


    public void sendMessage(String username) {
        SimpleMailMessage message = null;

        Player player = playerRepository.findByUsername(username);
        try {
            message = new SimpleMailMessage();
            message.setFrom(MESSAGE_FROM);
            message.setTo(player.getEmail());
            message.setSubject("Your Registration was successful");
            message.setText("Dear " + player.getUsername() + "! \n \n " +
                    "Thank you for your registration! \n" +
                    " your Activation code is "+ player.getActivation());

            javaMailSender.send(message);
            log.info("EMAIL SENT");

        } catch (Exception e) {
            log.error("Hiba e-mail küldéskor az alábbi címre: " + player.getEmail() + "  " + e);
        }


    }


}