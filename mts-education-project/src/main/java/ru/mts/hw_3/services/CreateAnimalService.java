package ru.mts.hw_3.services;
import java.math.BigDecimal;

import ru.mts.hw_3.animals.*;

public interface CreateAnimalService{
	
	String[] animalNames = {"Барсик", "Кузя", "Лео", "Рекс", "Госпар", "Ромул"};  //список возможных имен
	String[] animalCharacters = {"Агресиввный", "Ласковый", "Вредный", "Прожорливый", "Мстительный", "Игривый"}; //список возможных характеров
	String[] animalCase = {"Волк", "Акула", "Попугай", "Кот"}; //список возможных видов животных
	BigDecimal maxPrice = BigDecimal.valueOf(100.0); //максимальная цена за животное
	
	
	 default AbstractAnimal createRandomAnimal() {
		AbstractAnimal animal;

        int nameIndex = (int)(Math.random()*animalNames.length);     //выбираем рандомное имя
		int characterIndex = (int)(Math.random()*animalCharacters.length); //выбираем рандомный характер
		BigDecimal cost = maxPrice.multiply(BigDecimal.valueOf(Math.random())); //выбираем рандомную цену

		int caseIndex = (int)(Math.random()*animalCase.length); //выбираем рандомный вид животного

		switch (animalCase[caseIndex]) {
			case "Волк":
				animal = new Wolf("Тасманский сумчатый", animalNames[nameIndex], cost, animalCharacters[characterIndex]);
				break;
			case "Акула":
				animal = new Shark("Тигровая", animalNames[nameIndex], cost, animalCharacters[characterIndex]);
				break;
			case "Попугай":
				animal = new Parrot("Венесуэльский амазон", animalNames[nameIndex], cost, animalCharacters[characterIndex]);
				break;
			case "Кот":
				animal = new Cat("Мейн-кун", animalNames[nameIndex], cost, animalCharacters[characterIndex]);
				break;
			default:
				animal = null;
				break;
		}
		return animal;
	}


	public default void createAnimals() {
		System.out.println("Создали 10 животных:");
		
		// создаем массив 10 животных
		AbstractAnimal[] animals;
		animals = new AbstractAnimal[10];
		
		int count = 0; //счетчик по циклу
		while (count < 10){

			animals[count] = createRandomAnimal();

			animals[count].printAnimal();

			count++;
		}
		
	}
}