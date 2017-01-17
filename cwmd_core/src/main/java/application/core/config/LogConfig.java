package application.core.config;

import application.core.utils.logging.Log;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LogConfig {
    @Bean
    public Log getLogger() {
        return new Log();
    }
}
