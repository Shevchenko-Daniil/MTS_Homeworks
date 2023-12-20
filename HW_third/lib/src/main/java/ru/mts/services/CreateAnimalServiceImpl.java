package ru.mts.services;

import java.math.BigDecimal;

import ru.mts.animals.AbstractAnimal;
import ru.mts.animals.Cat;
import ru.mts.animals.Parrot;
import ru.mts.animals.Shark;
import ru.mts.animals.Wolf;

public class CreateAnimalServiceImpl implements CreateAnimalService{
	
	public void createAnimals(int numOfAnimals) {
		
		if(numOfAnimals < 0) {
			throw new IllegalArgumentException("Некорректное значение количества животных");
		}
		else {
		
			System.out.println("Создали " + numOfAnimals + " животных");
			
			//создаем массив животных длинной numOfAnimals
			AbstractAnimal[] animals;
			animals = new AbstractAnimal[numOfAnimals];
		
			for (int count = 0; count < numOfAnimals; count++) {
				int nameIndex = (int)(Math.random()*animalNames.length);     //выбираем рандомное имя
				int characterIndex = (int)(Math.random()*animalCharacters.length); //выбираем рандомный характер
				BigDecimal cost = maxPrice.multiply(BigDecimal.valueOf(Math.random())); //выбираем рандомную цену
			
				int caseIndex = (int)(Math.random()*animalCase.length); //выбираем рандомный вид животного
			
				switch (animalCase[caseIndex]) {
					case "Волк":
						animals[count] = new Wolf("Тасманский сумчатый", animalNames[nameIndex], cost, animalCharacters[characterIndex]);
						break;
					case "Акула":
						animals[count] = new Shark("Тигровая", animalNames[nameIndex], cost, animalCharacters[characterIndex]);
						break;
					case "Попугай":
						animals[count] = new Parrot("Венесуэльский амазон", animalNames[nameIndex], cost, animalCharacters[characterIndex]);
						break;
					case "Кот":
						animals[count] = new Cat("Мейн-кун", animalNames[nameIndex], cost, animalCharacters[characterIndex]);
						break;
				}
		
				System.out.println(animalCase[caseIndex] + 
					", Порода:" + animals[count].getBreed() + 
					", Имя:" + animals[count].getName() + 
					", Стоимость:" + animals[count].getCost() +
					", Характер:" + animals[count].getCharacter());	
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
			int nameIndex = (int)(Math.random()*animalNames.length);     //выбираем рандомное имя
			int characterIndex = (int)(Math.random()*animalCharacters.length); //выбираем рандомный характер
			BigDecimal cost = maxPrice.multiply(BigDecimal.valueOf(Math.random())); //выбираем рандомную цену
			
			int caseIndex = (int)(Math.random()*animalCase.length); //выбираем рандомный вид животного
			
			switch (animalCase[caseIndex]) {
				case "Волк":
					animals[count] = new Wolf("Тасманский сумчатый", animalNames[nameIndex], cost, animalCharacters[characterIndex]);
					break;
				case "Акула":
					animals[count] = new Shark("Тигровая", animalNames[nameIndex], cost, animalCharacters[characterIndex]);
					break;
				case "Попугай":
					animals[count] = new Parrot("Венесуэльский амазон", animalNames[nameIndex], cost, animalCharacters[characterIndex]);
					break;
				case "Кот":
					animals[count] = new Cat("Мейн-кун", animalNames[nameIndex], cost, animalCharacters[characterIndex]);
					break;
			}
		
			System.out.println(animalCase[caseIndex] + 
				", Порода:" + animals[count].getBreed() + 
				", Имя:" + animals[count].getName() + 
				", Стоимость:" + animals[count].getCost() +
				", Характер:" + animals[count].getCharacter());	
			count++;
		}
		while(count < 10);
	}
	
}