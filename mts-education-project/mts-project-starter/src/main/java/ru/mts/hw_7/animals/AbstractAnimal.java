package ru.mts.hw_7.animals;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;


public abstract class AbstractAnimal implements Animal {
	protected String breed;
	protected String name;
	protected BigDecimal cost;
	protected String character;
	protected LocalDate birthDate;

	public AbstractAnimal(String breed, String name, BigDecimal cost, String character, LocalDate birthDate) {
		if (BigDecimal.ZERO.compareTo(cost) >= 0) { //если цена отрицательна
			throw new IllegalArgumentException("Некорректное значение цены животного");
		}
		this.breed = breed;
		this.name = name;
		this.cost = cost.setScale(2, RoundingMode.HALF_UP);
		this.character = character;
		this.birthDate = birthDate;
	}

	/**
	 * Метод для вывода информации о животном в консоль
	 */
	public void printAnimal() {
		System.out.println(this.getClass().getSimpleName() +
				", Порода:" + this.breed +
				", Имя:" + this.name +
				", Стоимость:" + this.cost +
				", Характер:" + this.character +
				", Дата рождения:" + this.birthDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
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

	public LocalDate getBirthDate() { return this.birthDate;}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		AbstractAnimal that = (AbstractAnimal) o;
		return Objects.equals(breed, that.breed) && Objects.equals(name, that.name) && Objects.equals(cost, that.cost) && Objects.equals(character, that.character) && Objects.equals(birthDate, that.birthDate);
	}

	@Override
	public int hashCode() {
		return Objects.hash(breed, name, cost, character, birthDate);
	}
}