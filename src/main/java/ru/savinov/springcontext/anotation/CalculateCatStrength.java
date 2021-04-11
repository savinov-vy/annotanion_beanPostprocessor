package ru.savinov.springcontext.anotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Данная аннотация предназначена для инициализации private переменных на этапе передачи бинов BeanFactory
 * BeanPostProcessor -ами
 */

@Retention(RetentionPolicy.RUNTIME)
public @interface CalculateCatStrength {

    int minStrenght() default 1;

    int maxStrenght() default 2;
}
