package ru.mts.animals;

import java.math.BigDecimal;
import java.math.RoundingMode;



public abstract class AbstractAnimal implements Animal{
	protected String breed;
	protected String name;
	protected BigDecimal cost;
	protected String character;
	
	
	public String getBreed() {
		return this.breed;
	}
	
	public String getName() {
		return this.name;
	}
	
	public BigDecimal getCost() {
		return this.cost.setScale(2, RoundingMode.HALF_UP);
	}
	
	public String getCharacter() {
		return this.character;
	}
}