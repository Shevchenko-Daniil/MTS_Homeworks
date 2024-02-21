package ru.mts.hw_7;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.mts.hw_7.animals.AbstractAnimal;
import ru.mts.hw_7.animals.Cat;
import ru.mts.hw_7.animals.Wolf;
import ru.mts.hw_7.services.CreateAnimalServiceImpl;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest(classes = StarterTestConfiguration.class)
public class StarterTest {
    @Autowired
    private CreateAnimalServiceImpl createAnimalService;


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

        @Test
        @DisplayName("Test for negative cost of animal")
        public void illegalArgInConstructorTest(){
            Class<IllegalArgumentException> exceptionClass = IllegalArgumentException.class;
            assertThrows(exceptionClass, () -> new Cat("1", "2", BigDecimal.valueOf(-100.0), "3", LocalDate.of(2020, 3, 15)));
        }
    }

    @Nested
    @DisplayName("Tests for CreateAnimalService")
    public class CreateAnimalServiceTest{

        @Test
        @DisplayName("Test for negative value of numOfAnimals")
        public void createAnimalsIllegalArgTest(){
            Class<IllegalArgumentException> exceptionClass = IllegalArgumentException.class;
            assertThrows(exceptionClass, () -> createAnimalService.createAnimals(-10));
        }

        @Test
        @DisplayName("Test for method createAnimals(int numAnimals)")
        public void createAnimalsTest(){
            int numAnimals = 5;
            assertEquals(createAnimalService.createAnimals(5).length, numAnimals);

        }
    }
}
