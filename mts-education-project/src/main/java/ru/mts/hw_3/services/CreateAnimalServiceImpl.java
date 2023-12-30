package ru.mts.hw_3.services;

import java.math.BigDecimal;

import ru.mts.hw_3.animals.*;

public class CreateAnimalServiceImpl implements CreateAnimalService{
	
	public void createAnimals(int numOfAnimals) {
		
		if(numOfAnimals < 0) {
			throw new IllegalArgumentException("Некорректное значение количества животных");
		}
		else {
		
			System.out.println("Создали " + numOfAnimals + " животных:");
			
			//создаем массив животных длинной numOfAnimals
			AbstractAnimal[] animals;
			animals = new AbstractAnimal[numOfAnimals];
		
			for (int count = 0; count < numOfAnimals; count++) {

				animals[count] = createRandomAnimal();

				animals[count].printAnimal();

			}
		}
	}
	
	@Override
	public void createAnimals() {
		System.out.println("Создали 10 животных:");
		
		// создаем массив 10 животных
		AbstractAnimal[] animals;
		animals = new AbstractAnimal[10];
		
		int count = 0; //счетчик по циклу
		
		do {
			animals[count] = createRandomAnimal();

			animals[count].printAnimal();
			count++;
		}
		while(count < 10);
	}
	
}