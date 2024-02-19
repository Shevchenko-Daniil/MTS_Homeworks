package ru.mts.hw_7.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.mts.hw_7.animals.AbstractAnimal;

@Service
public class ScheduledService {

    @Autowired
    private AnimalsRepositoryImpl animalsRepository;

    @Scheduled(fixedRateString = "${application.scheduler.timeRate}")
    public void doAllMethods() {

        ////метод findLeapYearNames
        System.out.println("Метод findLeapYearNames");
        String[] leapNames = animalsRepository.findLeapYearNames();
        for(int i = 0; i< leapNames.length; i++){
            System.out.println(leapNames[i]);
        }

        //метод findOlderAnimal
        System.out.println();
        System.out.println("Метод findOlderAnimal");
        int minAge = 5;
        AbstractAnimal[] oldAnimals = animalsRepository.findOlderAnimal(minAge);
        for(int i = 0; i< oldAnimals.length; i++){
            oldAnimals[i].printAnimal();
        }

        //метод printDuplicate
        System.out.println();
        System.out.println("Метод printDuplicate");
        animalsRepository.printDuplicate();

        //метод findDuplicate
        System.out.println();
        System.out.println("Метод findDuplicate");
        AbstractAnimal[] duplicateAnimals = animalsRepository.findDuplicate();
        for(int i = 0; i< duplicateAnimals.length; i++){
            duplicateAnimals[i].printAnimal();
        }
    }
}
