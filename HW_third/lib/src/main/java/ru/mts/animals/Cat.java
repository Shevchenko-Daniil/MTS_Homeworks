package ru.mts.animals;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Cat extends Pet{
	
	public Cat(String breed, String name, BigDecimal cost, String character) {
		if(BigDecimal.valueOf(0.0).compareTo(cost) >= 0) { //если цена отрицательна
			throw new IllegalArgumentException("Некорректное значение цены животного");
		}
		this.breed = breed;
		this.name = name;
		this.cost = cost.setScale(2, RoundingMode.HALF_UP);
		this.character = character;
	}
	
}