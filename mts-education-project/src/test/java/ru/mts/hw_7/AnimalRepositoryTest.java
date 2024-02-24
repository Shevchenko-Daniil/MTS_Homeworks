package ru.mts.hw_7;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import ru.mts.hw_7.animals.*;
import ru.mts.hw_7.config.AnimalAutoConfiguration;
import ru.mts.hw_7.config.ConfigurationApp;
import ru.mts.hw_7.services.AnimalsRepositoryImpl;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(classes = {AnimalRepositoryTestConfiguration.class, AnimalAutoConfiguration.class, ConfigurationApp.class})
public class AnimalRepositoryTest {
   @Autowired
    @Qualifier("LeapYearRepo")
    AnimalsRepositoryImpl leapYearAnimalsRepository;

    @Autowired
    @Qualifier("OldAnimalsRepo")
    AnimalsRepositoryImpl oldAnimalsRepository;

    @Autowired
    @Qualifier("AnimalsRepo")
    AnimalsRepositoryImpl animalsRepository;

    AbstractAnimal[] animals;
    AbstractAnimal[] oldAnimals;


    //просто для проверки поднятия контекста
    @Test
    public void test(){
        System.out.println();
    }

    @Nested
    @DisplayName("Tests for positive outcomes")
    public class PositiveOutcomes {
        @Test
        @DisplayName("Test for findLeapYearNames method")
        public void findLeapYearNamesTest() {
            String[] leapYearNames = leapYearAnimalsRepository.findLeapYearNames();

            assertEquals(leapYearNames.length, 3);

        }

        @Test
        @DisplayName("Test for findDuplicate method")
        public void findDuplicateTest() {

            AbstractAnimal[] duplicateAnimal = animalsRepository.findDuplicate();
            assertEquals(3, duplicateAnimal.length);
        }

        @ParameterizedTest(name = "Test for animals over {arguments} years old")
        @ValueSource(ints = {5, 10, 15}) //разные значения количества лет
        @DisplayName("Test for animal's age")
        public void findOlderAnimalTest(int value) {

            //проверка положительных исходов
            if (value == 5) {
                assertEquals(6, oldAnimalsRepository.findOlderAnimal(value).length);
            } else if (value == 10) {
                assertEquals(3, oldAnimalsRepository.findOlderAnimal(value).length);
            } else if (value == 15) {
                assertEquals(2, oldAnimalsRepository.findOlderAnimal(value).length);
            }
        }
    }


    @Nested
    public class NegativeOutcomes {
        @Test
        @DisplayName("Test for illegal argument in method findOlderAnimal")
        public void findOlderIllegalArgTest() {
            int minAge = -3;
            Class<IllegalArgumentException> exceptionClass = IllegalArgumentException.class;
            assertThrows(exceptionClass, () -> animalsRepository.findOlderAnimal(minAge));
        }

        @Test
        @DisplayName("Test for null check")
        public void nullCheck() {
            AbstractAnimal[] animals = new AbstractAnimal[1];
            animals[0] = null;

            animalsRepository.setAnimals(animals);

            Class<IllegalArgumentException> exceptionClass = IllegalArgumentException.class;
            assertThrows(exceptionClass, () -> animalsRepository.findDuplicate());
            assertThrows(exceptionClass, () -> animalsRepository.findLeapYearNames());
            assertThrows(exceptionClass, () -> animalsRepository.findOlderAnimal(5));
        }
    }

}
