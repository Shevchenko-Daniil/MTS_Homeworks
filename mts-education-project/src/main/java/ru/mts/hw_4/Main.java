package ru.mts.hw_4;

import ru.mts.hw_4.services.CreateAnimalServiceImpl;
import ru.mts.hw_4.animals.*;
import ru.mts.hw_4.services.SearchService;
import ru.mts.hw_4.services.SearchServiceImpl;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Main {
	public static void main(String[] args) {

		//создаем сервис для создания животных
		CreateAnimalServiceImpl createService = new CreateAnimalServiceImpl();
		AbstractAnimal[] animalsSecond = createService.createAnimals(10);

		/* //вывод всех созданных животных
		System.out.println();
		System.out.println();
		System.out.println();
		for(int i = 0; i < animalsSecond.length; i++ ){
			animalsSecond[i].printAnimal();
		}
		System.out.println();
		System.out.println();
		 */

		// создаем сервис для поиска животных
		SearchServiceImpl searchService = new SearchServiceImpl();

		String[] names = searchService.findLeapYearNames(animalsSecond);
		System.out.println();
		System.out.println("Имена животных, рожденных в високосный год:");
		for(int i = 0; i < names.length; i++){
			System.out.println(names[i]);
		}
		System.out.println();

		int minAge = 10;
		AbstractAnimal[] olderAnimals = searchService.findOlderAnimal(animalsSecond, minAge);
		System.out.println("Животные, которые старше " + minAge + " лет:");
		for (int i = 0; i<olderAnimals.length; i++){
			olderAnimals[i].printAnimal();
		}
		System.out.println();System.out.println();

		System.out.println("Животные-дубликаты в массиве:");
		searchService.findDuplicate(animalsSecond);

		
	}
}