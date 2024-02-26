package ru.mts.hw_7;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import ru.mts.hw_7.config.AnimalConfig;
import ru.mts.hw_7.services.CreateAnimalServiceImpl;

@TestConfiguration
public class StarterTestConfiguration {

    @Bean
    public CreateAnimalServiceImpl createAnimalServiceImpl(AnimalConfig animalConfig){
        return new CreateAnimalServiceImpl(animalConfig);
    }
}
