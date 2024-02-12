package ru.mts.hw_7.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.mts.hw_7.animals.*;

@Configuration
@ConditionalOnClass(AbstractAnimal.class)
@EnableConfigurationProperties(AnimalProperties.class)
public class AnimalAutoConfiguration {
    @Autowired
    private AnimalProperties animalProperties;

    @Bean
    @ConditionalOnMissingBean
    public AnimalConfig animalConfig(){
        String[] names = animalProperties.getNames();

        AnimalConfig animalConfig = new AnimalConfig();
        animalConfig.put("names", names);
        return animalConfig;
    }

}
