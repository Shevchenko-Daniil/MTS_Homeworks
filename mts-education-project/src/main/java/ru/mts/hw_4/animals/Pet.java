package ru.mts.hw_4.animals;


import java.math.BigDecimal;
import java.time.LocalDate;

public abstract class Pet extends AbstractAnimal {

    public Pet(String breed, String name, BigDecimal cost, String character, LocalDate birthDate) {
        super(breed, name, cost, character, birthDate);
    }
}