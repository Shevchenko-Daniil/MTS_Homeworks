package ru.mts.animals;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Wolf extends Predator{
	
	public Wolf(String breed, String name, BigDecimal cost, String character) {
		if(cost.compareTo(BigDecimal.valueOf(0.0)) < 0) { //если цена отрицательна
			throw new IllegalArgumentException("Некорректное значение цены животного");
		}
		this.breed = breed;
		this.name = name;
		this.cost = cost.setScale(2, RoundingMode.HALF_UP);
		this.character = character;
	}
}