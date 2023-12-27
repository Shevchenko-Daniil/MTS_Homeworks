package ru.mts.hw_4;

import ru.mts.hw_4.services.CreateAnimalServiceImpl;
import ru.mts.hw_4.animals.*;
import ru.mts.hw_4.services.SearchService;
import ru.mts.hw_4.services.SearchServiceImpl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Main {
	public static void main(String[] args) {

		//создаем сервис для создания животных
		CreateAnimalServiceImpl createService = new CreateAnimalServiceImpl();
		AbstractAnimal[] animals = createService.createAnimals(10);


		// создаем сервис для поиска животных
		SearchServiceImpl searchService = new SearchServiceImpl();

		String[] names = searchService.findLeapYearNames(animals);
		System.out.println();
		System.out.println("Имена животных, рожденных в високосный год:");
		for(int i = 0; i < names.length; i++){
			System.out.println(names[i]);
		}
		System.out.println();

		int minAge = 10;
		AbstractAnimal[] olderAnimals = searchService.findOlderAnimal(animals, minAge);
		System.out.println("Животные, которые старше " + minAge + " лет:");
		for (int i = 0; i<olderAnimals.length; i++){
			olderAnimals[i].printAnimal();
		}
		System.out.println();System.out.println();


		System.out.println("Животные-дубликаты в массиве:");
		searchService.findDuplicate(animals);

		
	}
}