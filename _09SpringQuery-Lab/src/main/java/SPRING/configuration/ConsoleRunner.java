package SPRING.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class ConsoleRunner {



    //@Bean
    public ConsoleRunner createRunner() {
        return new ConsoleRunner();
    }
}
