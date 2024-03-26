package ru.mts.hw_7.services;

import org.springframework.context.annotation.Scope;
import ru.mts.hw_7.animals.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    default Map<String, List<AbstractAnimal>> createAnimals() {

        Map<String, List<AbstractAnimal>> animals = new HashMap<>();

        List<AbstractAnimal> cats = new ArrayList<>();
        animals.put("Cat", cats);
        List<AbstractAnimal> wolfs = new ArrayList<>();
        animals.put("Wolf", wolfs);
        List<AbstractAnimal> sharks = new ArrayList<>();
        animals.put("Shark", sharks);
        List<AbstractAnimal> parrots = new ArrayList<>();
        animals.put("Parrot", parrots);

        int count = 0;
        while(count < 10){
            AbstractAnimal animal = createRandomAnimal();
            Class<? extends AbstractAnimal> clazz = animal.getClass();
            String className = clazz.getSimpleName();

            animals.get(className).add(animal);
            count++;
        }
        return animals;

    }
    /**
     * @return Массив numOfAnimals случайных животных
     */
    Map<String, List<AbstractAnimal>> createAnimals(int numOfAnimals);
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