package ru.mts.hw_7.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.mts.hw_7.animals.AbstractAnimal;


import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

@Repository
public class AnimalsRepositoryImpl implements AnimalsRepository {
    private ArrayList<AbstractAnimal> animals;
    @Autowired
    private CreateAnimalService createAnimalService;

    @PostConstruct
    public void init(){
        animals = new ArrayList<>();
        Map<String, List<AbstractAnimal>> animalsMap = createAnimalService.createAnimals();
        for(Map.Entry<String, List<AbstractAnimal>> entry: animalsMap.entrySet()) {
            animals.addAll(entry.getValue());
        }
    }

    @Override
    public Map<String, LocalDate> findLeapYearNames(){
        checkingForNull(animals); //проверяем список

        Map<String, LocalDate> leapYearNames = new HashMap<>(); //создаем мапу под животных

        animals.stream()
                .filter(animal -> checkLeapYear(animal.getBirthDate().getYear()))
                .forEach(animal -> leapYearNames.put(animal.getClass().getSimpleName() + " " + animal.getName(),
                        animal.getBirthDate()));

        return leapYearNames;
    }

    @Override
    public Map<AbstractAnimal, Integer> findOlderAnimal(int minAge){
        checkingForNull(animals); //проверяем список
        if(minAge < 0){
            throw new IllegalArgumentException("Неверное значение возраста для сравнения");
        }

        Map<AbstractAnimal, Integer> olderAnimals = new HashMap<>(); //создаем мапу под животных

        animals.stream()
                .filter(animal -> animal.getBirthDate().plusYears(minAge).isBefore(LocalDate.now()))
                .forEach(animal -> olderAnimals.put(animal,
                        Period.between(animal.getBirthDate(), LocalDate.now()).getYears()));
        //если нет животных старше minAge
        if(olderAnimals.isEmpty()){
            AbstractAnimal oldAnimal = animals.stream()
                    .min(Comparator.comparing(AbstractAnimal::getBirthDate)).get();
            olderAnimals.put(oldAnimal, Period.between(oldAnimal.getBirthDate(), LocalDate.now()).getYears());
        }


        return olderAnimals;
    }


    @Override
    public Map<String, List<AbstractAnimal>> findDuplicate(){
        checkingForNull(animals); //проверяем список

        HashSet<AbstractAnimal> animalsSet = new HashSet<>();

        return animals.stream()
                .filter(animal -> !animalsSet.add(animal))
                .collect(groupingBy(animal -> animal.getClass().getSimpleName()));
    }

    @Override
    public void printDuplicate(){
        Map<String, List<AbstractAnimal>> duplicateAnimals = this.findDuplicate();
        ArrayList<List<AbstractAnimal>> duplicateListList= new ArrayList<>(duplicateAnimals.values());
        List<AbstractAnimal> duplicateList = duplicateListList.stream()
                        .flatMap(List::stream)
                        .collect(Collectors.toList());
        duplicateList.forEach(AbstractAnimal::printAnimal);
    }

    @Override
    public void findAverageAge(){
        checkingForNull(animals);
        System.out.println(
                animals.stream()
                .mapToInt(animal -> Period.between(animal.getBirthDate(), LocalDate.now()).getYears())
                .average()
                .orElseThrow(() -> new RuntimeException("Не удалось посчитать средний возраст"))
        );
    }

    @Override
    public List<AbstractAnimal> findOldAndExpensive(){
        checkingForNull(animals);
        double averageCost = animals.stream()
                .mapToDouble(animal -> animal.getCost().doubleValue())
                .average()
                .orElseThrow(() -> new RuntimeException("Не удалось посчитать средний возраст"));
        return animals.stream()
                .filter(animal -> (animal.getCost().doubleValue() > averageCost &&
                        Period.between(animal.getBirthDate(), LocalDate.now()).getYears() > 5))
                .sorted(Comparator.comparing(AbstractAnimal::getBirthDate))
                .collect(Collectors.toList());
    }

    @Override
    public List<String> findMinCostAnimals(){
        checkingForNull(animals);
        List<String> names = new ArrayList<>(3);
        List<AbstractAnimal> sortAnimals = animals.stream()
                .sorted(Comparator.comparing(AbstractAnimal::getCost))
                .collect(Collectors.toList());
        names.add(sortAnimals.get(0).getName());
        names.add(sortAnimals.get(1).getName());
        names.add(sortAnimals.get(2).getName());

        return names.stream()
                .sorted((name1, name2) -> -name1.compareTo(name2))
                .collect(Collectors.toList());
    }


    private void checkingForNull(ArrayList<AbstractAnimal> animals){
        for(int i = 0; i < animals.size(); i++){
            if(Objects.isNull(animals.get(i))){
                throw new IllegalArgumentException("В массиве присутствуют null объекты");
            }
        }
    }

    private boolean checkLeapYear(int year){
        return year % 4 == 0 && year % 100 != 0 || year % 400 == 0;
    }


}
