package ru.mts.hw_7.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.mts.hw_7.animals.AbstractAnimal;

import java.time.LocalDate;
import java.util.Map;

@Service
public class ScheduledService {

    @Autowired
    private AnimalsRepositoryImpl animalsRepository;

    @Scheduled(fixedRateString = "${application.scheduler.timeRate}")
    public void doAllMethods() {

        ////метод findLeapYearNames
        System.out.println("Метод findLeapYearNames");
        Map<String, LocalDate> leapAnimals = animalsRepository.findLeapYearNames();
        for(Map.Entry<String, LocalDate> entry: leapAnimals.entrySet()) {
            String key = entry.getKey();
            LocalDate value = entry.getValue();

            System.out.println(key + ":" + value);
        }

        //метод findOlderAnimal
        System.out.println();
        System.out.println("Метод findOlderAnimal");
        int minAge = 5;
        Map<AbstractAnimal, Integer> oldAnimals = animalsRepository.findOlderAnimal(minAge);
        for(Map.Entry<AbstractAnimal, Integer> entry: oldAnimals.entrySet()) {
            AbstractAnimal key = entry.getKey();
            key.printAnimal();
        }

        //метод printDuplicate
        System.out.println();
        System.out.println("Метод printDuplicate");
        animalsRepository.printDuplicate();

        //метод findDuplicate
        System.out.println();
        System.out.println("Метод findDuplicate");
        Map<String, Integer> duplicateAnimals = animalsRepository.findDuplicate();
        for(Map.Entry<String, Integer> entry: duplicateAnimals.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            System.out.println(key + "=" + value);
        }
    }
}
