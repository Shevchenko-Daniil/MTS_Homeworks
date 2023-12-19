package ru.mts.animals;

import java.math.BigDecimal;

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
	
}