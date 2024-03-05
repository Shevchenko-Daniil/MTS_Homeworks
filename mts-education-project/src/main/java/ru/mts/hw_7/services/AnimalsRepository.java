package ru.mts.hw_7.services;

import ru.mts.hw_7.animals.AbstractAnimal;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface AnimalsRepository {

    /**
     * @return Возвращает массив имен животных, которые родились в високосный год
     */
    Map<String, LocalDate> findLeapYearNames();

    /**
     * @param minAge - возраст, старше которого возвращаемые животные
     * @return Возвращает массив животных, возраст которых превышает minAge лет
     */
    Map<AbstractAnimal, Integer> findOlderAnimal(int minAge);

    /**
     * @return Возвращает массив животных-дубликатов
     */
    Map<String, Integer> findDuplicate();

    /**
     * Выводит массив животных-дубликатов в масииве animals
     */
    void printDuplicate();

    /**
     * Выовдит средний возраст списка животных
     */
    void findAverageAge();

    /**
     * @return Возвращает отсортированный по дате рождения список животных, которые старше 5 лет,
     * и стоимость которых больше средней
     */
    List<AbstractAnimal> findOldAndExpensive();

    /**
     *
     * @return Возвращает список имен, отсортированный в обратном алфавитном порядке, трех животных с самой низкой ценой
     */
    List<String> findMinCostAnimals();

}
