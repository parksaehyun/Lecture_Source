package config;

import exam03.ReCalculator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppCtx {

    @Bean
    public ReCalculator reCalculator() {
        return new ReCalculator();
    };
}
