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

        oldAnimals = new AbstractAnimal[11];
        oldAnimals[0] = new Cat("1", "Барсик", BigDecimal.valueOf(100.0), "11", LocalDate.of(2000, 3, 15));
        oldAnimals[1] = new Cat("1", "Барсик", BigDecimal.valueOf(100.0), "11", LocalDate.of(2020, 3, 15));
        oldAnimals[2] = new Cat("1", "Барсик", BigDecimal.valueOf(100.0), "11", LocalDate.of(2019, 1, 30));
        oldAnimals[3] = new Parrot("2", "Кузя", BigDecimal.valueOf(100.0), "11", LocalDate.of(2016, 5, 25));
        oldAnimals[4] = new Parrot("2", "Кузя", BigDecimal.valueOf(100.0), "11", LocalDate.of(2014, 1, 29));
        oldAnimals[5] = new Shark("3", "Ромул", BigDecimal.valueOf(100.0), "11", LocalDate.of(2014, 1, 28));
        oldAnimals[6] = new Shark("3", "Ромул", BigDecimal.valueOf(100.0), "11", LocalDate.of(2015, 7, 6));
        oldAnimals[7] = new Wolf("4", "Рекс", BigDecimal.valueOf(100.0), "11", LocalDate.of(2004, 6, 15));
        oldAnimals[8] = new Wolf("4", "Рекс", BigDecimal.valueOf(100.0), "11", LocalDate.of(2006, 5, 29));
        oldAnimals[9] = new Wolf("4", "Рекс", BigDecimal.valueOf(100.0), "11", LocalDate.of(2008, 10, 6));
        oldAnimals[10] = new Wolf("4", "Рекс", BigDecimal.valueOf(100.0), "11", LocalDate.of(2018, 8, 15));
    }

    @Nested
    public class AbstractAnimalTest {

        @Test
        @DisplayName("Test for method equals")
        public void checkEqualsAnimalsTest() {
            assertTrue(animals[0].equals(animals[1]));  //одинаковые классы с одинаковыми полями
            assertFalse(animals[0].equals(animals[2]));  // одинаковые классы с разными полями
            assertFalse(animals[0].equals(animals[3]));  // разные классы с одинаковыми полями
            assertFalse(animals[0].equals(null));   //сравнение с null


        }
    }
    @Nested
    public class SearchServiceImplTest{
        @Test
        @DisplayName("Test for leap year of birth")
        public void findLeapYearNamesTest(){
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
        }

        @ParameterizedTest(name = "Test for animals over {arguments} years old")
        @ValueSource(ints = {5, 10, 15}) //разные значения количества лет
        public void findOlderAnimalTest(int value){
            if(value == 5){
                assertEquals(9, searchService.findOlderAnimal(oldAnimals, value).length);
            }
            else if(value == 10){
                assertEquals(5, searchService.findOlderAnimal(oldAnimals, value).length);
            }
            else if(value == 15){
                assertEquals(4, searchService.findOlderAnimal(oldAnimals, value).length);
            }

        }

        @Test
        @DisplayName("Test for duplicate animals")
        public void findDuplicateTest(){
            AbstractAnimal[] duplicateAnimal =  searchService.findDuplicate(animals);
            assertEquals(3, duplicateAnimal.length);
        }
    }
}
