package ru.mts.animals;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Parrot extends Pet{
	
	public Parrot(String breed, String name, BigDecimal cost, String character) {
		this.breed = breed;
		this.name = name;
		this.cost = cost.setScale(2, RoundingMode.HALF_UP);
		this.character = character;
	}
}