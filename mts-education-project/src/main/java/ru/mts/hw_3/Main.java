package ru.mts.hw_3;

import ru.mts.hw_3.services.CreateAnimalServiceImpl;

public class Main{
	public static void main(String[] args) {
		
		CreateAnimalServiceImpl service = new CreateAnimalServiceImpl();
		service.createAnimals();
		service.createAnimals(5);
		
		
	}
}