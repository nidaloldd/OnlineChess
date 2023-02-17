package Java.web.src.main.java;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class OnlineChessApplication {

    public static void main(final String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(OnlineChessApplication.class, args);
        /*
        for (String beanDefinitionName : run.getBeanDefinitionNames()) {
            System.out.println(beanDefinitionName);
        }

         */
    }
}

