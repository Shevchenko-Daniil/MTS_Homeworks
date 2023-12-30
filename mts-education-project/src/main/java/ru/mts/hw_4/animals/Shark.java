package ru.mts.hw_4.animals;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Shark extends Predator {
	
	public Shark(String breed, String name, BigDecimal cost, String character, LocalDate birthDate) {
		super(breed, name, cost, character, birthDate);
	}
}