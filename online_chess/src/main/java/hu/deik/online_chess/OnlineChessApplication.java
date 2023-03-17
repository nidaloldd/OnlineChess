package hu.deik.online_chess;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EnableCaching
@EnableJpaRepositories
public class OnlineChessApplication {

    public static void main(final String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(OnlineChessApplication.class, args);

    }
}

