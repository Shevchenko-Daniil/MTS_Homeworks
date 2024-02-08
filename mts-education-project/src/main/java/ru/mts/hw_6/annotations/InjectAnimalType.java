package ru.mts.hw_6.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


//сделал свою аннотацию, чтобы искать по ней поле в BeanPostProcessor
@Retention(RetentionPolicy.RUNTIME)
public @interface InjectAnimalType {
}
