package ru.mts.hw_4.services;

import ru.mts.hw_4.animals.AbstractAnimal;

public class CreateAnimalServiceImpl implements CreateAnimalService {

	/**
	 * @return Массив numOfAnimals случайных животных
	 */
    public AbstractAnimal[] createAnimals(int numOfAnimals) {

        if (numOfAnimals < 0) {
            throw new IllegalArgumentException("Некорректное значение количества животных");
        }
		else {

            //System.out.println("Создали " + numOfAnimals + " животных:");

            //создаем массив животных длинной numOfAnimals
            AbstractAnimal[] animals;
            animals = new AbstractAnimal[numOfAnimals];

            for (int count = 0; count < numOfAnimals; count++) {

                animals[count] = createRandomAnimal();

                animals[count].printAnimal();

            }
			return animals;
        }

    }

	/**
	 * @return Массив 10 случайных животных
	 */
    @Override
    public AbstractAnimal[] createAnimals() {
        //System.out.println("Создали 10 животных:");

        // создаем массив 10 животных
        AbstractAnimal[] animals;
        animals = new AbstractAnimal[10];

        int count = 0; //счетчик по циклу

        do {
            animals[count] = createRandomAnimal();

            animals[count].printAnimal();
            count++;
        }
        while (count < 10);
		return animals;
    }

}