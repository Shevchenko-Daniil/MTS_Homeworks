package ru.mts.hw_7;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Profile;
import ru.mts.hw_7.animals.*;
import ru.mts.hw_7.services.AnimalsRepositoryImpl;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(classes = AnimalRepositoryTestConfiguration.class)
@Profile("test")
public class AnimalRepositoryTest {
    @Autowired
    AnimalsRepositoryImpl animalsRepository;

    @Autowired
    private ApplicationContext applicationContext;

    AbstractAnimal[] animals;
    AbstractAnimal[] oldAnimals;
//    @Value("${application-test.animal.names}")
  //  String[] animalsNames;

    @BeforeEach
    public void beforeEach() {

        animals = new AbstractAnimal[10];
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

        oldAnimals = new AbstractAnimal[8];
        oldAnimals[0] = new Cat("1", "Барсик", BigDecimal.valueOf(100.0), "11", LocalDate.now().minusYears(24));
        oldAnimals[1] = new Cat("1", "Барсик", BigDecimal.valueOf(100.0), "11", LocalDate.now().minusYears(4));
        oldAnimals[2] = new Cat("1", "Барсик", BigDecimal.valueOf(100.0), "11", LocalDate.now().minusYears(5));
        oldAnimals[3] = new Parrot("2", "Кузя", BigDecimal.valueOf(100.0), "11", LocalDate.now().minusYears(8));
        oldAnimals[4] = new Parrot("2", "Кузя", BigDecimal.valueOf(100.0), "11", LocalDate.now().minusYears(10).minusDays(1));
        oldAnimals[5] = new Shark("3", "Ромул", BigDecimal.valueOf(100.0), "11", LocalDate.now().minusYears(10).plusDays(1));
        oldAnimals[6] = new Shark("3", "Ромул", BigDecimal.valueOf(100.0), "11", LocalDate.now().minusYears(9).minusMonths(2));
        oldAnimals[7] = new Wolf("4", "Рекс", BigDecimal.valueOf(100.0), "11", LocalDate.now().minusYears(20));

    }

    @Test
    public void findLeapYearNamesTest(){
        AbstractAnimal[] leapYearAnimals = new AbstractAnimal[5];
        String[] animalsNames = {"Барсик", "Кузя", "Лео", "Рекс", "Госпар", "Ромул"};
        BigDecimal cost = BigDecimal.valueOf(100.0);

        leapYearAnimals[0] = new Cat("1", animalsNames[0], cost, "11", LocalDate.of(2020, 3, 15));
        leapYearAnimals[1] = new Wolf("1", animalsNames[1], cost, "11", LocalDate.of(2019, 3, 15));
        leapYearAnimals[2] = new Parrot("1", animalsNames[2], cost, "11", LocalDate.of(2000, 3, 15));
        leapYearAnimals[3] = new Shark("1", animalsNames[3], cost, "11", LocalDate.of(2002, 3, 15));
        leapYearAnimals[4] = new Cat("1", animalsNames[4], cost, "11", LocalDate.of(2004, 3, 15));

        animalsRepository.setAnimals(leapYearAnimals);
        String[] leapYearNames = animalsRepository.findLeapYearNames();

        assertEquals(leapYearNames.length, 3);


    }

    @Test
    public void findDuplicateTest(){

        animalsRepository.setAnimals(animals);
        AbstractAnimal[] duplicateAnimal =  animalsRepository.findDuplicate();
        assertEquals(3, duplicateAnimal.length);
    }

    @ParameterizedTest(name = "Test for animals over {arguments} years old")
    @ValueSource(ints = {5, 10, 15}) //разные значения количества лет
    @DisplayName("Test for animal's age")
    public void findOlderAnimalTest(int value){

        animalsRepository.setAnimals(oldAnimals);
        //проверка положительных исходов
        if(value == 5){
            assertEquals(6, animalsRepository.findOlderAnimal(value).length);
        }
        else if(value == 10){
            assertEquals(3, animalsRepository.findOlderAnimal(value).length);
        }
        else if(value == 15){
            assertEquals(2, animalsRepository.findOlderAnimal(value).length);
        }
    }

    @Test
    @DisplayName("Test for illegal argument in method findOlderAnimal")
    public void findOlderIllegalArgTest(){
        int minAge = -3;
        Class<IllegalArgumentException> exceptionClass = IllegalArgumentException.class;
        assertThrows(exceptionClass, () -> animalsRepository.findOlderAnimal(minAge));
    }

    @Test
    @DisplayName("Test for null check")
    public void nullCheck(){
        AbstractAnimal[] animals = new AbstractAnimal[1];
        animals[0] = null;

        animalsRepository.setAnimals(animals);

        Class<IllegalArgumentException> exceptionClass = IllegalArgumentException.class;
        assertThrows(exceptionClass, () -> animalsRepository.findDuplicate());
        assertThrows(exceptionClass, () -> animalsRepository.findLeapYearNames());
        assertThrows(exceptionClass, () -> animalsRepository.findOlderAnimal(5));
    }
}