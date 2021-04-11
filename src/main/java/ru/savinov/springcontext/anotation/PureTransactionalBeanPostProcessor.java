package ru.savinov.springcontext.anotation;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

public class PureTransactionalBeanPostProcessor implements BeanPostProcessor {

    private Map<String, Class<?>> beanMap = new HashMap<>();

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Class<?> beanClass = bean.getClass();

        /***
         * если над классом проходящего через построцессор аннотация которой мы пометили класс то ложим этот бин
         * в HashMap
         */
        if (beanClass.isAnnotationPresent(PureTransactional.class)) {
            beanMap.put(beanName, beanClass);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class<?> beanClass = beanMap.get(beanName);
        if (beanClass != null) {
            /***
             * Проверяем что работа метода postProcessAfterInitialization происходит с классом над которым стоит "наша
             * аннтоация(если есть в Хашмап то это она) далее в return возвращаем объект обернутый объектом прокси в
             * котором с помощью new InvocationHandler() реализована дополненная логика выполнения метода. Т.е.
             * в IoC контейнер попадет не оригинальный объект а прокси объект (переделанный метод которого, указан в
             * реализации new InvocationHandler(). Однако, переделанный метод имеет ссыклку на оригинальный метод ориги
             * нального объекта с помощью method.invoke(bean, args)". Таким образом при работе программы происходит сле-
             * дущее:
             * - программа обращается к IoC Контейнеру и вызывает метод объекта;
             * - в IoC контейнере лежит proxy объекта и вызывается метод этого proxy объекта;
             * - proxy метод отрабатывает первую часть дополненной логики см. строка 1-2
             * - proxy метод вызывает оригиналый метод и записывает результат;
             * - proxy метод выполняет вторую часть дополненной логики см. строка 4-6
             * - proxy метод отдает результат оригинального метода программе.
             *
             * Таким образом МЕТОД ВЕРНЕТ ТОТ ЖЕ РЕЗУЛЬТАТ как и без использования аннотации и прокси объектов, но
             * перед и после выполнением метода БУДЕТ ВЫПОЛНЕННА ДОПОЛНЕННАЯ АННОТАЦИЕЙ ЛОГИКА
             */

            return Proxy.newProxyInstance(beanClass.getClassLoader(), beanClass.getInterfaces(), new InvocationHandler() {

                @Override
                public Object invoke(Object o, Method method, Object[] args) throws Throwable {
                    System.out.println("Открываю транзакцию");                                  // строка 1
                    long before = System.nanoTime();                                            // строка 2
                    Object result = method.invoke(bean, args);                                  // строка 3
                    long after = System.nanoTime();                                             // строка 4
                    System.out.println("Метод работал "+ (after - before)/1000 + " мс");        // строка 5
                    System.out.println("Закрываю транзакцию");                                  // строка 6
                    return result;                                                              // строка 7
                }
            });
        }
        return bean;
    }
}
