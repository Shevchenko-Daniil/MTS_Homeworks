package ru.mts.hw_4.services;

import ru.mts.hw_4.animals.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;

public interface CreateAnimalService {

    String[] animalNames = {"Барсик", "Кузя", "Лео", "Рекс", "Госпар", "Ромул"};  //список возможных имен
    String[] animalCharacters = {"Агресиввный", "Ласковый", "Вредный", "Прожорливый", "Мстительный", "Игривый"}; //список возможных характеров
    String[] animalCase = {"Волк", "Акула", "Попугай", "Кот"}; //список возможных видов животных
    BigDecimal maxPrice = BigDecimal.valueOf(100.0); //максимальная цена за животное

    //метод для генерации случайного(и по виду тоже) животного
    default AbstractAnimal createRandomAnimal() {
        AbstractAnimal animal;

        int nameIndex = (int) (Math.random() * animalNames.length);     //выбираем рандомное имя
        int characterIndex = (int) (Math.random() * animalCharacters.length); //выбираем рандомный характер
        BigDecimal cost = maxPrice.multiply(BigDecimal.valueOf(Math.random())); //выбираем рандомную цену

        //генерируем рандомную дату рождения
        LocalDate startDate = LocalDate.of(2000, 1, 1); //начальная дата
        long start = startDate.toEpochDay();

        LocalDate endDate = LocalDate.now(); //конечная дата
        long end = endDate.toEpochDay();

        long randomEpochDay = ThreadLocalRandom.current().longs(start, end).findAny().getAsLong();
        LocalDate birthDate = LocalDate.ofEpochDay(randomEpochDay); //рандомная дата


        int caseIndex = (int) (Math.random() * animalCase.length); //выбираем рандомный вид животного

        switch (animalCase[caseIndex]) {
            case "Волк":
                animal = new Wolf("Тасманский сумчатый", animalNames[nameIndex], cost, animalCharacters[characterIndex], birthDate);
                break;
            case "Акула":
                animal = new Shark("Тигровая", animalNames[nameIndex], cost, animalCharacters[characterIndex], birthDate);
                break;
            case "Попугай":
                animal = new Parrot("Венесуэльский амазон", animalNames[nameIndex], cost, animalCharacters[characterIndex], birthDate);
                break;
            case "Кот":
                animal = new Cat("Мейн-кун", animalNames[nameIndex], cost, animalCharacters[characterIndex], birthDate);
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

            animals[count].printAnimal();

            count++;
        }
        return animals;

    }
}