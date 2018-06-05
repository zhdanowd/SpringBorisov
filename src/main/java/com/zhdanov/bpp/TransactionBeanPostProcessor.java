package com.zhdanov.bpp;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class TransactionBeanPostProcessor implements BeanPostProcessor {

    private Map<String, Class> map = new HashMap<>();

    @Override
    public Object postProcessBeforeInitialization(final Object o, final String s) throws BeansException {
        Class<?> originalClass = o.getClass();

        if (isTransactionMethodPresent(originalClass)) {
            map.put(s, originalClass);
        }

        return o;
    }

    @Override
    public Object postProcessAfterInitialization(final Object o, final String s) throws BeansException {
        Class<?> originalClass = o.getClass();
        if (map.containsKey(s)) {
            return Proxy.newProxyInstance(originalClass.getClassLoader(), originalClass.getInterfaces(),
                    invocationHandler(o, s));
        }
        return o;
    }

    private InvocationHandler invocationHandler(Object o, String beanName) {
        return (proxy, method, args) -> {
            Method originalMethod = map.get(beanName).getMethod(method.getName(), method.getParameterTypes());
            if(originalMethod.isAnnotationPresent(Transaction.class)) {
                System.out.println("transaction begin");
            }
            method.invoke(o, args);
            if(originalMethod.isAnnotationPresent(Transaction.class)) {
                System.out.println("transaction end");
            }

            return o;
        };
    }

    private Boolean isTransactionMethodPresent(Class clazz) {
        return Arrays.stream(clazz.getMethods())
                .anyMatch(method -> method.isAnnotationPresent(Transaction.class));
    }
}
