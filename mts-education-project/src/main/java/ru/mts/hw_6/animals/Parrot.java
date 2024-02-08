package ru.mts.hw_6.animals;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Parrot extends Pet {
	
	public Parrot(String breed, String name, BigDecimal cost, String character, LocalDate birthDate) {
		super(breed, name, cost, character, birthDate);
	}
}