package ru.mts.hw_3.animals;

import java.math.BigDecimal;
import java.math.RoundingMode;



public abstract class AbstractAnimal implements Animal{
	protected String breed;
	protected String name;
	protected BigDecimal cost;
	protected String character;

	public AbstractAnimal(String breed, String name, BigDecimal cost, String character){
		if(BigDecimal.ZERO.compareTo(cost) >= 0) { //если цена отрицательна
			throw new IllegalArgumentException("Некорректное значение цены животного");
		}
		this.breed = breed;
		this.name = name;
		this.cost = cost.setScale(2, RoundingMode.HALF_UP);
		this.character = character;
	}

	public void printAnimal(){
		System.out.println(this.getClass().getSimpleName() +
				", Порода:" + this.breed +
				", Имя:" + this.name +
				", Стоимость:" + this.cost +
				", Характер:" + this.character);
	}
	
	
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