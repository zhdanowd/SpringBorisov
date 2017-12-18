package com.zhdanov.bpp;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class BenchmarkBeanPostProcessor implements BeanPostProcessor {

    private Map<String, Class> beanOriginalClassMap = new HashMap<>();

    @Override
    public Object postProcessBeforeInitialization(final Object o, final String s) throws BeansException {
        final Class originalClass = o.getClass();
        if(isBenchmarkMethodPresent(o.getClass())){
            beanOriginalClassMap.put(s, originalClass);
        }
        return o;
    }

    @Override
    public Object postProcessAfterInitialization(final Object o, final String s) throws BeansException {
        Class<?> originalClass = o.getClass();

        if (beanOriginalClassMap.containsKey(s)){
            return Proxy.newProxyInstance(originalClass.getClassLoader(), originalClass.getInterfaces(),
                    invocationHandler(o, s));
        } else {
            return o;
        }
    }

    private InvocationHandler invocationHandler(final Object o  , String beanName) {
        return ((proxy, method, args) -> {
            Method originalMethod = beanOriginalClassMap.get(beanName).getMethod(method.getName(), method.getParameterTypes());
            long before = System.nanoTime();
            Object retVal = method.invoke(o, args);
            long after = System.nanoTime();
            if (originalMethod.isAnnotationPresent(Benchmark.class)) {
                System.out.println(after - before);
            }
            return retVal;
        });
    }

    private Boolean isBenchmarkMethodPresent(Class clazz) {
        return Arrays.stream(clazz.getMethods())
                .anyMatch(method -> method.isAnnotationPresent(Benchmark.class));
    }
}
