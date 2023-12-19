package ru.mts.services;
import java.math.BigDecimal;

import ru.mts.animals.*;

public interface CreateAnimalService{
	
	String[] animalNames = {"Барсик", "Кузя", "Лео", "Рекс", "Госпар", "Ромул"};  //список возможных имен
	String[] animalCharacters = {"Агресиввный", "Ласковый", "Вредный", "Прожорливый", "Мстительный", "Игривый"}; //список ыозсожных характеров
	String[] animalCase = {"Волк", "Акула", "Попугай", "Кот"}; //список возсожных видов животных
	BigDecimal maxPrice = BigDecimal.valueOf(100.0); //максимальная цена за животное
	
	
	public default void createAnimals() {
		System.out.println("Создали 10 животных:");
		
		// создаем массив 10 животных
		AbstractAnimal[] animals;
		animals = new AbstractAnimal[10];
		
		int count = 0; //счетчик по циклу
		while (count < 10){
			
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
		
	}
}