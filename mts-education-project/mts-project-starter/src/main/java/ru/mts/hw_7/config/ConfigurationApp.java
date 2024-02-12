package ru.mts.hw_7.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.mts.hw_7.services.CreateAnimalService;
import ru.mts.hw_7.services.CreateAnimalServiceImpl;

@Configuration
public class ConfigurationApp {
    @Bean
    public CreateAnimalService createAnimalService(AnimalConfig animalConfig) {
        return new CreateAnimalServiceImpl(animalConfig);
    }


}
