package ru.mts.hw_4.services;

import ru.mts.hw_4.animals.AbstractAnimal;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

public interface SearchService {
    /**
     * @param animals - входной массив жисотных
     * @return Возвращает массив имен животных, которые родились в високосный год
     */
    String[] findLeapYearNames(AbstractAnimal[] animals);

    /**
     * @param animals - входной массив животных
     * @param minAge - возраст, старше которого возвращаемые животные
     * @return Возвращает массив животных, возраст которых превышает minAge лет
     */
    AbstractAnimal[] findOlderAnimal(AbstractAnimal[] animals, int minAge);

    /**
     * @param animals - входной массив животных
     * @return Возвращает массив животных-дубликатов
     */
    AbstractAnimal[] findDuplicate(AbstractAnimal[] animals);

    /**
     * @param animals - входной массив животных
     * Выводит массив животных-дубликатов в масииве animals
     */
    void printDuplicate(AbstractAnimal[] animals);
}
