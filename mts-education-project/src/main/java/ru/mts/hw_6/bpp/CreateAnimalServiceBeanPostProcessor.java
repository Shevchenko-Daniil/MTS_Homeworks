package ru.mts.hw_6.bpp;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ReflectionUtils;
import ru.mts.hw_6.services.CreateAnimalService;
import ru.mts.hw_6.services.InjectAnimalType;

import java.lang.reflect.Field;



@Configuration
public class CreateAnimalServiceBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {

        Field[] fields = bean.getClass().getDeclaredFields();
        for (Field field: fields){
            InjectAnimalType annotation = field.getAnnotation(InjectAnimalType.class);
            if(annotation != null){
                field.setAccessible(true);
                ReflectionUtils.setField(field, bean, CreateAnimalService.generateRandomType());
            }
        }


        return bean;
    }
}
