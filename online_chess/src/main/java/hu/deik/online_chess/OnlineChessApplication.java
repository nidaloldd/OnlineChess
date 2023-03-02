package hu.deik.online_chess;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class OnlineChessApplication {

    public static void main(final String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(OnlineChessApplication.class, args);

    }
}

