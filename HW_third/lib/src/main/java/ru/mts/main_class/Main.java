package ru.mts.main_class;

import ru.mts.services.CreateAnimalServiceImpl;

public class Main{
	public static void main(String[] args) {
		
		CreateAnimalServiceImpl service = new CreateAnimalServiceImpl();
		service.createAnimals();
		service.createAnimals(5);
		
		
	}
}