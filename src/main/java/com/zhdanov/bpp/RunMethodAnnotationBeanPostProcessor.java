package com.zhdanov.bpp;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class RunMethodAnnotationBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(final Object o, final String s) {
        Class beanClass = o.getClass();
        Method[] methods = beanClass.getMethods();
        Arrays.stream(methods).filter(method -> method.isAnnotationPresent(RunThisMethod.class)).forEach((Method method) -> {
            try {
                method.invoke(o);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        });
        return o;
    }

    @Override
    public Object postProcessAfterInitialization(final Object o, final String s) throws BeansException {
        return o;
    }
}
