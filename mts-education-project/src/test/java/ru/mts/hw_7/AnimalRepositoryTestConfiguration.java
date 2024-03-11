package ru.mts.hw_7;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import ru.mts.hw_7.repositories.AnimalsRepositoryImpl;


@TestConfiguration
public class AnimalRepositoryTestConfiguration {

    @Bean(name = "LeapYearRepo")
    public AnimalsRepositoryImpl leapYearAnimalRepository() {
        return new AnimalsRepositoryImpl();
    }

    @Bean(name = "OldAnimalsRepo")
    public AnimalsRepositoryImpl oldAnimalRepository() {
        return new AnimalsRepositoryImpl();
    }

    @Bean(name = "AnimalsRepo")
    public AnimalsRepositoryImpl animalRepository() {
        return new AnimalsRepositoryImpl();
    }

    @Bean(name = "StreamMethodsRepo")
    public AnimalsRepositoryImpl streamMethodsRepository() {
        return new AnimalsRepositoryImpl();
    }
}
