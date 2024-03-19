package ru.mts.hw_7.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.mts.hw_7.animals.AbstractAnimal;
import ru.mts.hw_7.exceptions.InvalidDataException;
import ru.mts.hw_7.exceptions.InvalidInputException;
import ru.mts.hw_7.services.CreateAnimalService;


import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

@Repository
public class AnimalsRepositoryImpl implements AnimalsRepository {
    private ArrayList<AbstractAnimal> animals;
    @Autowired
    private CreateAnimalService createAnimalService;

    @PostConstruct
    public void init() {
        animals = new ArrayList<>();
        Map<String, List<AbstractAnimal>> animalsMap = createAnimalService.createAnimals();
        for (Map.Entry<String, List<AbstractAnimal>> entry : animalsMap.entrySet()) {
            animals.addAll(entry.getValue());
        }
    }

    @Override
    public Map<String, LocalDate> findLeapYearNames() {
        checkingForNull(animals); //проверяем список

        Map<String, LocalDate> leapYearNames = new HashMap<>(); //создаем мапу под животных

        animals.stream()
                .filter(animal -> checkLeapYear(animal.getBirthDate().getYear()))
                .forEach(animal -> leapYearNames.put(animal.getClass().getSimpleName() + " " + animal.getName(),
                        animal.getBirthDate()));

        return leapYearNames;
    }

    @Override
    public Map<AbstractAnimal, Integer> findOlderAnimal(int minAge) {
        checkingForNull(animals); //проверяем список
        if (minAge < 0) {
            throw new InvalidInputException("Неверное значение возраста для сравнения");
        }

        Map<AbstractAnimal, Integer> olderAnimals = new HashMap<>(); //создаем мапу под животных

        animals.stream()
                .filter(animal -> animal.getBirthDate().plusYears(minAge).isBefore(LocalDate.now()))
                .forEach(animal -> olderAnimals.put(animal,
                        Period.between(animal.getBirthDate(), LocalDate.now()).getYears()));
        //если нет животных старше minAge
        if (olderAnimals.isEmpty()) {
            Optional<AbstractAnimal> oldAnimalOpt = animals.stream()
                    .min(Comparator.comparing(AbstractAnimal::getBirthDate));
            if (oldAnimalOpt.isPresent()) {
                AbstractAnimal oldAnimal = oldAnimalOpt.get();
                olderAnimals.put(oldAnimal, Period.between(oldAnimal.getBirthDate(), LocalDate.now()).getYears());
            }
        }


        return olderAnimals;
    }

    @Override
    public Map<String, List<AbstractAnimal>> findDuplicate() {
        checkingForNull(animals); //проверяем список

        HashSet<AbstractAnimal> animalsSet = new HashSet<>();

        return animals.stream()
                .filter(animal -> !animalsSet.add(animal))
                .collect(groupingBy(animal -> animal.getClass().getSimpleName()));
    }

    @Override
    public void printDuplicate() {
        Map<String, List<AbstractAnimal>> duplicateAnimals = this.findDuplicate();
        ArrayList<List<AbstractAnimal>> duplicateListList = new ArrayList<>(duplicateAnimals.values());
        List<AbstractAnimal> duplicateList = duplicateListList.stream()
                .flatMap(List::stream)
                .collect(Collectors.toList());
        duplicateList.forEach(AbstractAnimal::printAnimal);
    }

    @Override
    public void findAverageAge(Collection<AbstractAnimal> animals) {
        checkingForNull(animals);
        System.out.println(
                animals.stream()
                        .mapToInt(animal -> Period.between(animal.getBirthDate(), LocalDate.now()).getYears())
                        .average()
                        .orElseThrow(() -> new RuntimeException("Не удалось посчитать средний возраст"))
        );
    }

    @Override
    public List<AbstractAnimal> findOldAndExpensive(Collection<AbstractAnimal> animals) {
        checkingForNull(animals);

        double averageCost = animals.stream()
                .mapToDouble(animal -> animal.getCost().doubleValue())
                .average()
                .orElseThrow(() -> new RuntimeException("Не удалось посчитать средний возраст"));

        Predicate<AbstractAnimal> expensiveAndOld = animal ->
                animal.getCost().doubleValue() > averageCost &&
                        Period.between(animal.getBirthDate(), LocalDate.now()).getYears() > 5;
        return animals.stream()
                .filter(expensiveAndOld)
                .sorted((animal1, animal2) -> -animal1.getBirthDate().compareTo(animal2.getBirthDate()))
                .collect(Collectors.toList());
    }

    @Override
    public List<String> findMinCostAnimals(Collection<AbstractAnimal> animals) {
        checkingForNull(animals);
        return animals.stream()
                .sorted(Comparator.comparing(AbstractAnimal::getCost))
                .limit(3)
                .map(AbstractAnimal::getName)
                .sorted((name1, name2) -> -name1.compareTo(name2))
                .collect(Collectors.toList());
    }

    private void checkingForNull(Collection<AbstractAnimal> animals) {
        if(animals == null){
            throw new InvalidDataException("Массив не заполнен");
        }
        if(animals.contains(null)){
            throw new InvalidDataException("В массиве присутствуют null объекты");
        }
    }

    private boolean checkLeapYear(int year) {
        return year % 4 == 0 && year % 100 != 0 || year % 400 == 0;
    }
}
