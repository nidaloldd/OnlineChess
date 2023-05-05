package hu.deik.online_chess;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.CrossOrigin;


@SpringBootApplication
@EnableCaching
@EnableJpaRepositories
@CrossOrigin
public class OnlineChessApplication {

    public static void main(final String[] args) {
        SpringApplication.run(OnlineChessApplication.class, args);
    }
}

