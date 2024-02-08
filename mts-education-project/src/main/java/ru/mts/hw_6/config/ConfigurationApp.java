package ru.mts.hw_6.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.mts.hw_6.services.CreateAnimalService;
import ru.mts.hw_6.services.CreateAnimalServiceImpl;

@Configuration
public class ConfigurationApp {
    @Bean
    public CreateAnimalService createAnimalService() {
        return new CreateAnimalServiceImpl();
    }


}
