package ru.mts.hw_4.services;

import ru.mts.hw_4.animals.AbstractAnimal;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Objects;

public class SearchServiceImpl implements SearchService {
    //метод проверяет элементы входного массива на null
    private void checkingForNull(AbstractAnimal[] animals){
        for(int i = 0; i < animals.length; i++){
            if(Objects.isNull(animals[i])){
                throw new IllegalArgumentException("В массиве присутствуют null объекты");
            }
        }
    }
    public String[] findLeapYearNames(AbstractAnimal[] animals){
        checkingForNull(animals); //проверяем массив
        ArrayList<String> LeapYearNames = new ArrayList<String>();  //создаем массив для имен

        for(int i =0; i < animals.length; i++){
            if(animals[i].getBirthDate().getYear()%4 == 0 &&
                    (animals[i].getBirthDate().getYear()%100 != 0 || animals[i].getBirthDate().getYear()%400 == 0)){
                LeapYearNames.add(animals[i].getName()); //добавляем имя в массив
            }
        }
        return LeapYearNames.toArray(new String[0]);
    }
    public AbstractAnimal[] findOlderAnimal(AbstractAnimal[] animals, int minAge){
        checkingForNull(animals); //проверяем массив
        ArrayList<AbstractAnimal> olderAnimal = new ArrayList<AbstractAnimal>();  //создаем массив для животных

        for(int i = 0; i < animals.length; i++){
            if(Period.between(animals[i].getBirthDate(), LocalDate.now()).getYears() > minAge){
                olderAnimal.add(animals[i]); //добавляем животное в массив
            }
        }
        return olderAnimal.toArray(new AbstractAnimal[0]);
    }

    public void findDuplicate(AbstractAnimal[] animals){
        checkingForNull(animals); //проверяем массив

        for(int i = 0; i < animals.length; i++){
            for(int j = i+1; j < animals.length; j++){
                if(animals[i].equals(animals[j])){
                    animals[i].printAnimal();
                }
            }
        }
    }
}
