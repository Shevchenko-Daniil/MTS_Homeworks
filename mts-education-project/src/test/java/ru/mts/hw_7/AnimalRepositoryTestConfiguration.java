package ru.mts.hw_7;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import ru.mts.hw_7.services.AnimalsRepositoryImpl;


@TestConfiguration
public class AnimalRepositoryTestConfiguration {
    @Bean
    @Primary
    public AnimalsRepositoryImpl animalRepository(){
        return new AnimalsRepositoryImpl();
    }

}
