package ru.mts.hw_6.services;

import ru.mts.hw_6.animals.AbstractAnimal;

public interface AnimalsRepository {

    /**
     * @return Возвращает массив имен животных, которые родились в високосный год
     */
    String[] findLeapYearNames();

    /**
     * @param minAge - возраст, старше которого возвращаемые животные
     * @return Возвращает массив животных, возраст которых превышает minAge лет
     */
    AbstractAnimal[] findOlderAnimal(int minAge);

    /**
     * @return Возвращает массив животных-дубликатов
     */
    AbstractAnimal[] findDuplicate();

    /**
     * Выводит массив животных-дубликатов в масииве animals
     */
    void printDuplicate();


}
