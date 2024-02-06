package ru.mts.hw_4;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import ru.mts.hw_4.animals.AbstractAnimal;
import ru.mts.hw_4.animals.*;
import ru.mts.hw_4.services.SearchServiceImpl;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;


public class AnimalServiceTest {
    private AbstractAnimal[] animals; //массив для тестов на equals, високосный год и дубликаты
    private AbstractAnimal[] oldAnimals; // массив для тестов на возраст
    private SearchServiceImpl searchService;


    @BeforeEach
    public void beforeEach() {
        searchService = new SearchServiceImpl();

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

    @Nested
    @DisplayName("Tests for class AbstractAnimal")
    public class AbstractAnimalTest {

        @Test
        @DisplayName("Test for method equals")
        public void checkEqualsAnimalsTest() {
            //тестовые данные
            AbstractAnimal animal1 = new Cat("1", "Барсик", BigDecimal.valueOf(100.0), "11", LocalDate.of(2020, 3, 15));
            AbstractAnimal animal2 = new Cat("1", "Барсик", BigDecimal.valueOf(100.0), "11", LocalDate.of(2020, 3, 15));
            AbstractAnimal animal3 = new Cat("1", "Пушок", BigDecimal.valueOf(10.0), "11", LocalDate.of(2019, 3, 15));
            AbstractAnimal animal4 = new Wolf("1", "Барсик", BigDecimal.valueOf(100.0), "11", LocalDate.of(2020, 3, 15));

            assertTrue(animal1.equals(animal2));  //одинаковые классы с одинаковыми полями
            assertFalse(animal1.equals(animal3));  // одинаковые классы с разными полями
            assertFalse(animal1.equals(animal4));  // разные классы с одинаковыми полями
            assertFalse(animal1.equals(null));   //сравнение с null


        }
    }
    @Nested
    @DisplayName("Test for class SearchService")
    public class SearchServiceImplTest{
        @Test
        @DisplayName("Test for leap year of birth")
        public void findLeapYearNamesTest(){

            //проверка положительных исходов
            String[] leapYearNames = new String[8];
            leapYearNames[0] = "Барсик";
            leapYearNames[1] = "Барсик";
            leapYearNames[2] = "Госпар";
            leapYearNames[3] = "Барсик";
            leapYearNames[4] = "Рекс";
            leapYearNames[5] = "Ромул";
            leapYearNames[6] = "Барсик";
            leapYearNames[7] = "Рекс";
            assertArrayEquals(leapYearNames, searchService.findLeapYearNames(animals));

            //проверка негативных исходов
            Class<IllegalArgumentException> exceptionClass = IllegalArgumentException.class;
            AbstractAnimal[] nullAnimal = new AbstractAnimal[1];
            nullAnimal[0] = null;
            assertThrows(exceptionClass, () -> searchService.findLeapYearNames(nullAnimal));
        }

        @ParameterizedTest(name = "Test for animals over {arguments} years old")
        @ValueSource(ints = {5, 10, 15}) //разные значения количества лет
        @DisplayName("Test for animal's age")
        public void findOlderAnimalTest(int value){

            //проверка положительных исходов
            if(value == 5){
                assertEquals(6, searchService.findOlderAnimal(oldAnimals, value).length);
            }
            else if(value == 10){
                assertEquals(3, searchService.findOlderAnimal(oldAnimals, value).length);
            }
            else if(value == 15){
                assertEquals(2, searchService.findOlderAnimal(oldAnimals, value).length);
            }

            //проверка отрицательных исходов
            Class<IllegalArgumentException> exceptionClass = IllegalArgumentException.class;
            AbstractAnimal[] nullAnimal = new AbstractAnimal[1];
            nullAnimal[0] = null;
            assertThrows(exceptionClass, () -> searchService.findOlderAnimal(nullAnimal, 10));
            assertThrows(exceptionClass, () -> searchService.findOlderAnimal(oldAnimals, -2));

        }

        @Test
        @DisplayName("Test for duplicate animals")
        public void findDuplicateTest(){

            //проверка положительных исходов
            AbstractAnimal[] duplicateAnimal =  searchService.findDuplicate(animals);
            assertEquals(3, duplicateAnimal.length);

            //проверка отрицательных исходов
            Class<IllegalArgumentException> exceptionClass = IllegalArgumentException.class;
            AbstractAnimal[] nullAnimal = new AbstractAnimal[1];
            nullAnimal[0] = null;
            assertThrows(exceptionClass, () -> searchService.findDuplicate(nullAnimal));

        }
    }
}
