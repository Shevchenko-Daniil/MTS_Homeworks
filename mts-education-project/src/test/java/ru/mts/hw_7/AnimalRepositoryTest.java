package ru.mts.hw_7;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.util.ReflectionTestUtils;
import ru.mts.hw_7.animals.*;
import ru.mts.hw_7.config.AnimalAutoConfiguration;
import ru.mts.hw_7.config.ConfigurationApp;
import ru.mts.hw_7.services.AnimalsRepositoryImpl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(classes = {AnimalRepositoryTestConfiguration.class, AnimalAutoConfiguration.class, ConfigurationApp.class})
@ActiveProfiles("test")
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

    @Value("${application-test.animal.names}")
    String[] animalsNames;

    ArrayList<AbstractAnimal> animals;
    ArrayList<AbstractAnimal> oldAnimals;
    ArrayList<AbstractAnimal> leapYearAnimals;

    @Autowired
    private ApplicationContext applicationContext;

    @BeforeEach
    public void setOldAnimalsRepository(){
        oldAnimals = new ArrayList<>();
        BigDecimal cost = BigDecimal.valueOf(100.0);

        oldAnimals.add(new Cat("1", "Барсик", cost, "11", LocalDate.now().minusYears(24)));
        oldAnimals.add(new Cat("1", "Барсик", cost, "11", LocalDate.now().minusYears(4)));
        oldAnimals.add(new Cat("1", "Барсик", cost, "11", LocalDate.now().minusYears(5)));
        oldAnimals.add(new Parrot("2", "Кузя", cost, "11", LocalDate.now().minusYears(8)));
        oldAnimals.add(new Parrot("2", "Кузя", cost, "11", LocalDate.now().minusYears(10).minusDays(1)));
        oldAnimals.add(new Shark("3", "Ромул", cost, "11", LocalDate.now().minusYears(10).plusDays(1)));
        oldAnimals.add(new Shark("3", "Ромул", cost, "11", LocalDate.now().minusYears(9).minusMonths(2)));
        oldAnimals.add(new Wolf("4", "Рекс", cost, "11", LocalDate.now().minusYears(20)));

        ReflectionTestUtils.setField(oldAnimalsRepository, "animals", oldAnimals);
    }
    @BeforeEach
    public void setLeapYearAnimalsRepository(){
        leapYearAnimals = new ArrayList<>();
        BigDecimal cost = BigDecimal.valueOf(100.0);

        leapYearAnimals.add(new Cat("1", animalsNames[0], cost, "11", LocalDate.of(2020, 3, 15)));
        leapYearAnimals.add(new Wolf("1", animalsNames[1], cost, "11", LocalDate.of(2019, 3, 15)));
        leapYearAnimals.add(new Parrot("1", animalsNames[2], cost, "11", LocalDate.of(2000, 3, 15)));
        leapYearAnimals.add(new Shark("1", animalsNames[3], cost, "11", LocalDate.of(2002, 3, 15)));
        leapYearAnimals.add(new Cat("1", animalsNames[4], cost, "11", LocalDate.of(2004, 3, 15)));

        ReflectionTestUtils.setField(leapYearAnimalsRepository, "animals", leapYearAnimals);
    }
    @BeforeEach
    public void setAnimalsRepository(){
        animals = new ArrayList<>();

        animals.add(new Cat("1", "Барсик", BigDecimal.valueOf(100.0), "11", LocalDate.of(2020, 3, 15)));
        animals.add(new Cat("1", "Барсик", BigDecimal.valueOf(100.0), "11", LocalDate.of(2020, 3, 15)));
        animals.add(new Cat("1", "Госпар", BigDecimal.valueOf(10.0), "11", LocalDate.of(2000, 3, 15)));
        animals.add(new Wolf("1", "Барсик", BigDecimal.valueOf(100.0), "11", LocalDate.of(2020, 3, 15)));
        animals.add(new Cat("1", "Кузя", BigDecimal.valueOf(100.0), "11", LocalDate.of(1900, 3, 15)));
        animals.add(new Parrot("2", "Ромул", BigDecimal.valueOf(100.0), "11", LocalDate.of(2022, 3, 15)));
        animals.add(new Parrot("2", "Рекс", BigDecimal.valueOf(100.0), "11", LocalDate.of(1904, 3, 15)));
        animals.add(new Shark("3", "Ромул", BigDecimal.valueOf(100.0), "11", LocalDate.of(2016, 3, 15)));
        animals.add(new Wolf("1", "Барсик", BigDecimal.valueOf(100.0), "11", LocalDate.of(2020, 3, 15)));
        animals.add(new Parrot("2", "Рекс", BigDecimal.valueOf(100.0), "11", LocalDate.of(1904, 3, 15)));

        ReflectionTestUtils.setField(animalsRepository, "animals", animals);
    }

    @Nested
    @DisplayName("Tests for positive outcomes")
    public class PositiveOutcomes {
        @Test
        @DisplayName("Test for findLeapYearNames method")
        public void findLeapYearNamesTest() {
            Map<String, LocalDate> leapYearNames = leapYearAnimalsRepository.findLeapYearNames();
            Map<String, LocalDate> truLeapYearNames = new HashMap<>();
            truLeapYearNames.put("Cat " + animalsNames[0], LocalDate.of(2020, 3, 15));
            truLeapYearNames.put("Parrot " + animalsNames[2], LocalDate.of(2000, 3, 15));
            truLeapYearNames.put("Cat " + animalsNames[4], LocalDate.of(2004, 3, 15));

            assertEquals(leapYearNames, truLeapYearNames);

        }

        @Test
        @DisplayName("Test for findDuplicate method")
        public void findDuplicateTest() {

            Map<String, Integer> duplicateAnimal = animalsRepository.findDuplicate();
            Map<String, Integer> trueDuplicateAnimals = new HashMap<>();
            trueDuplicateAnimals.put("Parrot", 1);
            trueDuplicateAnimals.put("Cat", 1);
            trueDuplicateAnimals.put("Wolf", 1);
            assertEquals(trueDuplicateAnimals, duplicateAnimal);
        }

        @Test
        @DisplayName("Test for findOlderAnimal method without old animals")
        public void findDuplicateNoDuplicatesTest(){
            int minAge = 30;
            Map<AbstractAnimal, Integer> noOldAnimals = oldAnimalsRepository.findOlderAnimal(minAge);
            Map<AbstractAnimal, Integer> oldest = new HashMap<>();
            oldest.put(new Cat("1", "Барсик", BigDecimal.valueOf(100.0), "11", LocalDate.now().minusYears(24)), 24);
            assertEquals(oldest, noOldAnimals);
        }

        @ParameterizedTest(name = "Test for animals over {arguments} years old")
        @ValueSource(ints = {5, 10, 15}) //разные значения количества лет
        @DisplayName("Test for animal's age")
        public void findOlderAnimalTest(int value) {

            //проверка положительных исходов
            if (value == 5) {
                assertEquals(6, oldAnimalsRepository.findOlderAnimal(value).size());
            } else if (value == 10) {
                assertEquals(3, oldAnimalsRepository.findOlderAnimal(value).size());
            } else if (value == 15) {
                assertEquals(2, oldAnimalsRepository.findOlderAnimal(value).size());
            }
        }
    }


    @Nested
    @DisplayName("Tests for negative outcomes")
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
            ArrayList<AbstractAnimal> animals = new ArrayList<>();
            animals.add(null);

            ReflectionTestUtils.setField(animalsRepository, "animals", animals);

            Class<IllegalArgumentException> exceptionClass = IllegalArgumentException.class;
            assertThrows(exceptionClass, () -> animalsRepository.findDuplicate());
            assertThrows(exceptionClass, () -> animalsRepository.findLeapYearNames());
            assertThrows(exceptionClass, () -> animalsRepository.findOlderAnimal(5));
        }
    }

}
