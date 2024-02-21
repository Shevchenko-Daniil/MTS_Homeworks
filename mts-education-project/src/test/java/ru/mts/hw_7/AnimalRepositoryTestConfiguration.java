package ru.mts.hw_7;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.test.context.TestPropertySource;
import ru.mts.hw_7.services.AnimalsRepositoryImpl;
import ru.mts.hw_7.services.ScheduledService;

@TestConfiguration
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
}
