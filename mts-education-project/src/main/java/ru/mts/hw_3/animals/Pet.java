package ru.mts.hw_3.animals;


import java.math.BigDecimal;

public abstract class Pet extends AbstractAnimal{

    public Pet(String breed, String name, BigDecimal cost, String character) {
        super(breed, name, cost, character);
    }
}