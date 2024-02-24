package ru.mts.hw_7;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;
import ru.mts.hw_7.animals.*;
import ru.mts.hw_7.services.AnimalsRepositoryImpl;

import java.math.BigDecimal;
import java.time.LocalDate;


@TestConfiguration
@Profile("test")
public class AnimalRepositoryTestConfiguration {

   @Value("${application-test.animal.names}")
   String[] animalsNames;

    @Bean(name = "LeapYearRepo")
    public AnimalsRepositoryImpl leapYearAnimalRepository(){
        AnimalsRepositoryImpl animalsRepository = new AnimalsRepositoryImpl();
        AbstractAnimal[] leapYearAnimals = new AbstractAnimal[5];
        BigDecimal cost = BigDecimal.valueOf(100.0);

        leapYearAnimals[0] = new Cat("1", animalsNames[0], cost, "11", LocalDate.of(2020, 3, 15));
        leapYearAnimals[1] = new Wolf("1", animalsNames[1], cost, "11", LocalDate.of(2019, 3, 15));
        leapYearAnimals[2] = new Parrot("1", animalsNames[2], cost, "11", LocalDate.of(2000, 3, 15));
        leapYearAnimals[3] = new Shark("1", animalsNames[3], cost, "11", LocalDate.of(2002, 3, 15));
        leapYearAnimals[4] = new Cat("1", animalsNames[4], cost, "11", LocalDate.of(2004, 3, 15));

        animalsRepository.setAnimals(leapYearAnimals);

        return animalsRepository;
    }

    @Bean(name = "OldAnimalsRepo")
    public AnimalsRepositoryImpl oldAnimalRepository(){
        AnimalsRepositoryImpl animalsRepository = new AnimalsRepositoryImpl();
        AbstractAnimal[] oldAnimals = new AbstractAnimal[8];
        BigDecimal cost = BigDecimal.valueOf(100.0);

        oldAnimals[0] = new Cat("1", "Барсик", BigDecimal.valueOf(100.0), "11", LocalDate.now().minusYears(24));
        oldAnimals[1] = new Cat("1", "Барсик", BigDecimal.valueOf(100.0), "11", LocalDate.now().minusYears(4));
        oldAnimals[2] = new Cat("1", "Барсик", BigDecimal.valueOf(100.0), "11", LocalDate.now().minusYears(5));
        oldAnimals[3] = new Parrot("2", "Кузя", BigDecimal.valueOf(100.0), "11", LocalDate.now().minusYears(8));
        oldAnimals[4] = new Parrot("2", "Кузя", BigDecimal.valueOf(100.0), "11", LocalDate.now().minusYears(10).minusDays(1));
        oldAnimals[5] = new Shark("3", "Ромул", BigDecimal.valueOf(100.0), "11", LocalDate.now().minusYears(10).plusDays(1));
        oldAnimals[6] = new Shark("3", "Ромул", BigDecimal.valueOf(100.0), "11", LocalDate.now().minusYears(9).minusMonths(2));
        oldAnimals[7] = new Wolf("4", "Рекс", BigDecimal.valueOf(100.0), "11", LocalDate.now().minusYears(20));

        animalsRepository.setAnimals(oldAnimals);

        return animalsRepository;
    }

    @Bean(name = "AnimalsRepo")
    public AnimalsRepositoryImpl animalRepository(){
        AnimalsRepositoryImpl animalsRepository = new AnimalsRepositoryImpl();
        AbstractAnimal[] animals = new AbstractAnimal[10];
        BigDecimal cost = BigDecimal.valueOf(100.0);

        animals[0] = new Cat("1", "Барсик", BigDecimal.valueOf(100.0), "11", LocalDate.of(2020, 3, 15));
        animals[1] = new Cat("1", "Барсик", BigDecimal.valueOf(100.0), "11", LocalDate.of(2020, 3, 15));
        animals[2] = new Cat("1", "Госпар", BigDecimal.valueOf(10.0), "11", LocalDate.of(2000, 3, 15));
        animals[3] = new Wolf("1", "Барсик", BigDecimal.valueOf(100.0), "11", LocalDate.of(2020, 3, 15));
        animals[4] = new Cat("1", "Кузя", BigDecimal.valueOf(100.0), "11", LocalDate.of(1900, 3, 15));
        animals[5] = new Parrot("2", "Ромул", BigDecimal.valueOf(100.0), "11", LocalDate.of(2022, 3, 15));
        animals[6] = new Parrot("2", "Рекс", BigDecimal.valueOf(100.0), "11", LocalDate.of(1904, 3, 15));
        animals[7] = new Shark("3", "Ромул", BigDecimal.valueOf(100.0), "11", LocalDate.of(2016, 3, 15));
        animals[8] = new Wolf("1", "Барсик", BigDecimal.valueOf(100.0), "11", LocalDate.of(2020, 3, 15));
        animals[9] = new Parrot("2", "Рекс", BigDecimal.valueOf(100.0), "11", LocalDate.of(1904, 3, 15));

        animalsRepository.setAnimals(animals);

        return animalsRepository;
    }
}
