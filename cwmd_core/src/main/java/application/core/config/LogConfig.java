package application.core.config;

import application.core.utils.logging.Log;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration of server logger
 */
@Configuration
public class LogConfig {

    /**
     * Getting our logger
     * @return The logger
     */
    @Bean
    public Log getLogger() {
        return new Log();
    }
}
