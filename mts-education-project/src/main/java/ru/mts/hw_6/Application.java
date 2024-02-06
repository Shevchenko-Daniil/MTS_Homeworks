package ru.mts.hw_6;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import ru.mts.hw_6.animals.AbstractAnimal;
import ru.mts.hw_6.services.AnimalsRepository;

@ComponentScan
public class Application {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Application.class);
        AnimalsRepository bean = context.getBean(AnimalsRepository.class);

        //метод findLeapYearNames
        System.out.println("Метод findLeapYearNames");
        String[] leapNames = bean.findLeapYearNames();
        for(int i = 0; i< leapNames.length; i++){
            System.out.println(leapNames[i]);
        }

        //метод findOlderAnimal
        System.out.println();
        System.out.println("Метод findOlderAnimal");
        int minAge = 5;
        AbstractAnimal[] oldAnimals = bean.findOlderAnimal(minAge);
        for(int i = 0; i< oldAnimals.length; i++){
            oldAnimals[i].printAnimal();
        }

        //метод findDuplicate
        System.out.println();
        System.out.println("Метод findDuplicate");
        bean.printDuplicate();

        context.close();




    }
}
