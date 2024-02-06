package ru.mts.hw_6.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mts.hw_6.animals.AbstractAnimal;
import ru.mts.hw_6.animals.Cat;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;


public class AnimalsRepositoryImpl implements AnimalsRepository{
    private AbstractAnimal[] animals;
    @Autowired
    private CreateAnimalServiceImpl createAnimalService;

    @PostConstruct
    public void init(){
        System.out.println("ВЫЗВАЛ!");
        animals = createAnimalService.createAnimals(10);
        //animals = new AbstractAnimal[1];
        //animals[0] = new Cat("1", "1", BigDecimal.valueOf(100.0), "1", LocalDate.now().minusYears(24));
    }

    public String[] findLeapYearNames(){
        checkingForNull(animals); //проверяем массив
        ArrayList<String> LeapYearNames = new ArrayList<String>();  //создаем массив для имен

        for(int i =0; i < animals.length; i++){
            if(checkLeapYear(animals[i].getBirthDate().getYear())){
                LeapYearNames.add(animals[i].getName()); //добавляем имя в массив
            }
        }
        return LeapYearNames.toArray(new String[0]);
    }

    public AbstractAnimal[] findOlderAnimal(int minAge){
        checkingForNull(animals); //проверяем массив
        ArrayList<AbstractAnimal> olderAnimal = new ArrayList<AbstractAnimal>();  //создаем массив для животных

        if(minAge < 0){
            throw new IllegalArgumentException("Неверное значение возраста для сравнения");
        }

        long nowDateEpoch = LocalDate.now().toEpochDay();

        for(int i = 0; i < animals.length; i++){
            long birthDateEpoch = animals[i].getBirthDate().plusYears(minAge).toEpochDay(); //прбавляем к дате орождения minAge
            if(birthDateEpoch < nowDateEpoch){
                olderAnimal.add(animals[i]); //добавляем животное в массив
            }
        }
        return olderAnimal.toArray(new AbstractAnimal[0]);
    }


    public AbstractAnimal[] findDuplicate(){
        checkingForNull(animals); //проверяем массив
        ArrayList<AbstractAnimal> duplicateAnimal = new ArrayList<AbstractAnimal>();  //создаем массив для дубликатов

        HashSet<AbstractAnimal> animalsSet = new HashSet<>();

        for(int i = 0; i < animals.length; i++){
            if(!animalsSet.add(animals[i])){
                duplicateAnimal.add(animals[i]);
            }
        }

        return duplicateAnimal.toArray(new AbstractAnimal[0]);
    }

    public void printDuplicate(){
        AbstractAnimal[] duplicateAnimal = this.findDuplicate();

        for(int i = 0; i < duplicateAnimal.length; i++){
            duplicateAnimal[i].printAnimal();
        }
    }


    private void checkingForNull(AbstractAnimal[] animals){
        for(int i = 0; i < animals.length; i++){
            if(Objects.isNull(animals[i])){
                throw new IllegalArgumentException("В массиве присутствуют null объекты");
            }
        }
    }

    private boolean checkLeapYear(int year){
        return year % 4 == 0 && year % 100 != 0 || year % 400 == 0;
    }


}
