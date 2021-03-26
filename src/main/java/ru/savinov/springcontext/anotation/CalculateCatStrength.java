package ru.savinov.springcontext.anotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CalculateCatStrength {

    int minStrenght() default 1;

    int maxStrenght() default 2;
}
