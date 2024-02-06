package ru.mts.hw_6;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import ru.mts.hw_6.services.AnimalsRepository;

@ComponentScan
public class Application {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(Application.class);
        AnimalsRepository bean = context.getBean(AnimalsRepository.class);

        String[] names = bean.findLeapYearNames();

        System.out.println(names);





    }
}
