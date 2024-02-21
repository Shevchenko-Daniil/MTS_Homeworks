package ru.mts.hw_7;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.TestPropertySource;
import ru.mts.hw_7.services.AnimalsRepositoryImpl;
import ru.mts.hw_7.services.ScheduledService;

import java.util.Arrays;

@TestConfiguration
@Profile("test")
//@TestPropertySource(locations = "mts-education-project/src/test/resources/application-test.yml")
//@ConfigurationProperties(prefix = "application-test.animal")
public class AnimalRepositoryTestConfiguration {

    @Value("${application-test.animal.names}")
    private String[] names;
    @Bean
    public String[] animalsNames(){
        return names;
    }
    @Bean
    @Primary
    public AnimalsRepositoryImpl animalRepository(){
        return new AnimalsRepositoryImpl();
    }

    @Bean
    public CommandLineRunner run(ApplicationContext appContext) {
        return args -> {

            String[] beans = appContext.getBeanDefinitionNames();
            Arrays.stream(beans).sorted().forEach(System.out::println);

        };
    }
}
