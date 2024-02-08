package ru.mts.hw_6.services;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.mts.hw_6.animals.*;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;


@Scope(value = "prototype")
public interface CreateAnimalService {


    //метод для генерации случайного(и по виду тоже) животного
    default AbstractAnimal createRandomAnimal() {
        AbstractAnimal animal;

        String name = generateRandomName();
        BigDecimal cost = generateRandomCost();
        String character = generateRandomCharacter();
        LocalDate birthDate = generateRandomDate();
        AnimalsTypes type = generateRandomType();


        switch (type) {
            case WOLF:
                animal = new Wolf("Тасманский сумчатый", name, cost, character, birthDate);
                break;
            case  SHARK:
                animal = new Shark("Тигровая", name, cost, character, birthDate);
                break;
            case PARROT:
                animal = new Parrot("Венесуэльский амазон",name, cost, character, birthDate);
                break;
            case CAT:
                animal = new Cat("Мейн-кун", name, cost, character, birthDate);
                break;
            default:
                animal = null;
                break;
        }
        return animal;
    }

    /**
     * @return Массив 10 случайных животных
     */
    default AbstractAnimal[] createAnimals() {
        //System.out.println("Создали 10 животных:");

        // создаем массив 10 животных
        AbstractAnimal[] animals;
        animals = new AbstractAnimal[10];

        int count = 0; //счетчик по циклу
        while (count < 10) {

            animals[count] = createRandomAnimal();


            count++;
        }
        return animals;

    }
    //генерирует случайное имя
    private String generateRandomName(){
        int nameIndex = (int) (Math.random() * AnimalName.values().length);
        return AnimalName.values()[nameIndex].getTitle();
    }
    //генерирует случайный характер
    private String generateRandomCharacter(){
        int characterIndex = (int) (Math.random() * AnimalCharacter.values().length);
        return AnimalCharacter.values()[characterIndex].getTitle();
    }
    //генерирует случайную стоимость (вплоть до максимальной цены)
    private BigDecimal generateRandomCost(){
        BigDecimal maxPrice = BigDecimal.valueOf(100.0); //максимальная цена за животное
        return maxPrice.multiply(BigDecimal.valueOf(Math.random())); //выбираем рандомную цену
    }
    //генерирует случайную дату рождения
    private LocalDate generateRandomDate(){
        LocalDate startDate = LocalDate.of(2000, 1, 1); //начальная дата
        long start = startDate.toEpochDay();

        LocalDate endDate = LocalDate.now(); //конечная дата
        long end = endDate.toEpochDay();

        long randomEpochDay = ThreadLocalRandom.current().longs(start, end).findAny().getAsLong();
        return LocalDate.ofEpochDay(randomEpochDay);
    }
    //генерирует случайный тип животного
    static AnimalsTypes generateRandomType(){
        int caseIndex = (int) (Math.random() * AnimalsTypes.values().length);
        return AnimalsTypes.values()[caseIndex];
    }
}