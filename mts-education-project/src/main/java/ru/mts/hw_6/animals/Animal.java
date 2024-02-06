package ru.mts.hw_6.animals;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface Animal{
	/**
	 * getBreed() возвращает породу животного в строковом типе 
	 */
	public String getBreed();
	/**
	 * getName() возвращает имя животного в строковом типе 
	 */
	public String getName();
	/**
	 * getCost() возвращает стоимость животного в формате BigDecimal и 
	 * округленный до двух знаков после запятой
	 */
	public BigDecimal getCost();
	/**
	 * getCharacter() возвращает характер животного в строковом типе 
	 */
	public String getCharacter();

	/**
	 * getBirthDate() возвращает дату рождения животного в формате LocalDate
	 *
	 */
	public LocalDate getBirthDate();

	/**
	 * Выводит в консоль информацию о животном
	 */
	public void printAnimal();
	
}