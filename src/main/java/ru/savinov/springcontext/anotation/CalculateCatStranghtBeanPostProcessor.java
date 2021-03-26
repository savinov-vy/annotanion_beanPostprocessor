package ru.savinov.springcontext.anotation;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.util.ReflectionUtils;
import java.lang.reflect.Field;
import java.util.Random;


public class CalculateCatStranghtBeanPostProcessor implements BeanPostProcessor {

    /** все бины проходят через эти методы.
     необходимо вычислить необходимый по наличии аннотации
     */
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Field[] decalaredFields = bean.getClass().getFields();
        for (Field field : decalaredFields) {
            CalculateCatStrength annotation = field.getAnnotation(CalculateCatStrength.class);
            if (annotation != null) {
                int minStrenght = annotation.minStrenght();
                int maxStrenght = annotation.maxStrenght();
                Random random = new Random();
                int result = minStrenght + random.nextInt(maxStrenght - minStrenght);
                field.setAccessible(true);
                ReflectionUtils.setField(field, bean, result);
            }
        }
        return bean;
    }
    /**
     *  между этими медодами срабатывает инит метод бина, аннотированный @PostConstruct
     */

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
