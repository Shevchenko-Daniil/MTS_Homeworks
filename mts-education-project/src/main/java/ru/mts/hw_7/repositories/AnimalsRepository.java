package ru.mts.hw_7.repositories;

import ru.mts.hw_7.animals.AbstractAnimal;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface AnimalsRepository {

    /**
     * @return Возвращает пару Имя-дата рождения животных, рожденных в високосный год
     */
    Map<String, LocalDate> findLeapYearNames();

    /**
     * @param minAge - возраст, старше которого возвращаемые животные
     * @return Возвращает пару животное-возраст животных, возраст которых превышает minAge лет
     */
    Map<AbstractAnimal, Integer> findOlderAnimal(int minAge);

    /**
     * @return Возвращает пары Тип-список дубликатов
     */
    Map<String, List<AbstractAnimal>> findDuplicate();

    /**
     * Выводит массив животных-дубликатов в масииве animals
     */
    void printDuplicate();

    /**
     * Выовдит средний возраст списка животных
     */
    void findAverageAge(Collection<AbstractAnimal> animals);

    /**
     * @return Возвращает отсортированный по дате рождения список животных, которые старше 5 лет,
     * и стоимость которых больше средней
     */
    List<AbstractAnimal> findOldAndExpensive(Collection<AbstractAnimal> animals);

    /**
     * @return Возвращает список имен, отсортированный в обратном алфавитном порядке, трех животных с самой низкой ценой
     */
    List<String> findMinCostAnimals(Collection<AbstractAnimal> animals);

}
