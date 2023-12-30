package ru.mts.hw_4;

import ru.mts.hw_4.services.CreateAnimalServiceImpl;
import ru.mts.hw_4.animals.*;
import ru.mts.hw_4.services.SearchService;
import ru.mts.hw_4.services.SearchServiceImpl;

import java.math.BigDecimal;
import java.time.LocalDate;


public class Main {
	public static void main(String[] args) {

		//создаем сервис для создания животных
		CreateAnimalServiceImpl createService = new CreateAnimalServiceImpl();
		AbstractAnimal[] animals = createService.createAnimals(10);


		// создаем сервис для поиска животных
		SearchServiceImpl searchService = new SearchServiceImpl();


		//ищем животных, рожденных в високосный год
		String[] names = searchService.findLeapYearNames(animals);
		System.out.println();
		System.out.println("Имена животных, рожденных в високосный год:");
		for(int i = 0; i < names.length; i++){
			System.out.println(names[i]);
		}
		System.out.println();


		//ищем животных старше 10 лет
		int minAge = 10;
		AbstractAnimal[] olderAnimals = searchService.findOlderAnimal(animals, minAge);

		System.out.println("Животные, которые старше " + minAge + " лет:");
		for (int i = 0; i<olderAnimals.length; i++){
			olderAnimals[i].printAnimal();
		}
		System.out.println();System.out.println();


		//проверяем корректность работы поиска дубликатов
		Wolf[] wolfs = new Wolf[5];
		BigDecimal cost = BigDecimal.valueOf(10.0);
		LocalDate birthDate = LocalDate.of(2016, 10, 20);
		wolfs[0] = new Wolf("Обычный", "1", cost, "Агрессивный", birthDate);
		wolfs[1] = new Wolf("Обычный", "2", cost, "Агрессивный", birthDate);
		wolfs[2] = new Wolf("Обычный", "2", cost, "Агрессивный", birthDate);
		wolfs[3] = new Wolf("Обычный", "2", cost, "Агрессивный", birthDate);
		wolfs[4] = new Wolf("Обычный", "2", cost, "Агрессивный", birthDate);

		System.out.println("Животные-дубликаты в массиве:");
		searchService.findDuplicate(wolfs);

		
	}
}