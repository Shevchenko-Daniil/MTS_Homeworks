package ru.mts.hw_7.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.mts.hw_7.animals.AbstractAnimal;


import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;

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

        for(int i =0; i < animals.size(); i++){
            if(checkLeapYear(animals.get(i).getBirthDate().getYear())){
                String key = animals.get(i).getClass().getSimpleName() + " " + animals.get(i).getName();
                leapYearNames.put(key, animals.get(i).getBirthDate());
            }
        }
        return leapYearNames;
    }

    @Override
    public Map<AbstractAnimal, Integer> findOlderAnimal(int minAge){
        checkingForNull(animals); //проверяем список
        if(minAge < 0){
            throw new IllegalArgumentException("Неверное значение возраста для сравнения");
        }

        Map<AbstractAnimal, Integer> olderAnimals = new HashMap<>(); //создаем мапу под животных

        long nowDateEpoch = LocalDate.now().toEpochDay();
        AbstractAnimal oldAnimal = animals.get(0);

        for(int i = 0; i < animals.size(); i++){
            long birthDateEpoch = animals.get(i).getBirthDate().plusYears(minAge).toEpochDay(); //прбавляем к дате орождения minAge
            //ищем самое старое животное
            if(birthDateEpoch < oldAnimal.getBirthDate().plusYears(minAge).toEpochDay()){
                oldAnimal = animals.get(i);
            }
            if(birthDateEpoch < nowDateEpoch){
                Integer age = Period.between(animals.get(i).getBirthDate(), LocalDate.now()).getYears();
                olderAnimals.put(animals.get(i), age);
            }
        }
        if(olderAnimals.isEmpty()){
            olderAnimals.put(oldAnimal, (Integer) Period.between(oldAnimal.getBirthDate(), LocalDate.now()).getYears());
        }
        return olderAnimals;
    }


    @Override
    public Map<String, Integer> findDuplicate(){
        checkingForNull(animals); //проверяем список

        Map<String, Integer> duplicateAnimals = new HashMap<>();
        duplicateAnimals.put("Cat", 0);
        duplicateAnimals.put("Wolf", 0);
        duplicateAnimals.put("Shark", 0);
        duplicateAnimals.put("Parrot", 0);


        HashSet<AbstractAnimal> animalsSet = new HashSet<>();

        for(int i = 0; i < animals.size(); i++){
            if(!animalsSet.add(animals.get(i))){
                String className = animals.get(i).getClass().getSimpleName();
                duplicateAnimals.put(className, duplicateAnimals.get(className) + 1);
            }
        }

        Iterator<Map.Entry<String, Integer>> itr = duplicateAnimals.entrySet().iterator();
        while(itr.hasNext()) {
            Map.Entry<String, Integer> entry =  itr.next();
            if(entry.getValue() == 0){
                itr.remove();
            }
        }

        return duplicateAnimals;
    }

    @Override
    public void printDuplicate(){
        Map<String, Integer> duplicateAnimals = this.findDuplicate();
        duplicateAnimals.forEach((key, value) -> System.out.println(key + "=" + value));
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
