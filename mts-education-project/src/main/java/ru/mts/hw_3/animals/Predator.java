package ru.mts.hw_3.animals;


import java.math.BigDecimal;

public abstract class Predator extends AbstractAnimal{

    public Predator(String breed, String name, BigDecimal cost, String character) {
        super(breed, name, cost, character);
    }
}