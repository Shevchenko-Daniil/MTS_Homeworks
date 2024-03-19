package ru.mts.hw_7.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.mts.hw_7.animals.AbstractAnimal;
import ru.mts.hw_7.exceptions.unchecked_exceptions.InvalidDataException;
import ru.mts.hw_7.exceptions.unchecked_exceptions.InvalidInputException;
import ru.mts.hw_7.repositories.AnimalsRepositoryImpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ScheduledService {

    @Autowired
    private AnimalsRepositoryImpl animalsRepository;

    @Scheduled(fixedRateString = "${application.scheduler.timeRate}")
    public void doAllMethods() {
        //метод findLeapYearNames
        try {
            System.out.println("Метод findLeapYearNames");
            Map<String, LocalDate> leapAnimals = animalsRepository.findLeapYearNames();
            for (Map.Entry<String, LocalDate> entry : leapAnimals.entrySet()) {
                String key = entry.getKey();
                LocalDate value = entry.getValue();

                System.out.println(key + ":" + value);
            }
        }
        catch (InvalidDataException e){
            System.out.println("Invalid data: " + e.getMessage());
        }

        //метод findOlderAnimal
        try {
            System.out.println();
            System.out.println("Метод findOlderAnimal");
            int minAge = 5;
            Map<AbstractAnimal, Integer> oldAnimals = animalsRepository.findOlderAnimal(minAge);
            for (Map.Entry<AbstractAnimal, Integer> entry : oldAnimals.entrySet()) {
                AbstractAnimal key = entry.getKey();
                key.printAnimal();
            }
        }
        catch (InvalidDataException e1){
            System.out.println("Invalid data: " + e1.getMessage());
        }
        catch (InvalidInputException e2){
            System.out.println("Invalid input: " + e2.getMessage());
        }


        //метод printDuplicate
        try {
            System.out.println();
            System.out.println("Метод printDuplicate");
            animalsRepository.printDuplicate();
        }
        catch (InvalidDataException e){
            System.out.println("Invalid data: " + e.getMessage());
        }

        //метод findDuplicate
        try {
            System.out.println();
            System.out.println("Метод findDuplicate");
            Map<String, List<AbstractAnimal>> duplicateAnimals = animalsRepository.findDuplicate();
            ArrayList<List<AbstractAnimal>> duplicateListList = new ArrayList<>(duplicateAnimals.values());
            List<AbstractAnimal> duplicateList = duplicateListList.stream()
                    .flatMap(List::stream)
                    .collect(Collectors.toList());
            duplicateList.forEach(AbstractAnimal::printAnimal);
        }
        catch (InvalidDataException e){
            System.out.println("Invalid data: " + e.getMessage());
        }
    }
}
