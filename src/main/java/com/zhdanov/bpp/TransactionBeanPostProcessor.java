package com.zhdanov.bpp;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class TransactionBeanPostProcessor implements BeanPostProcessor {

    private Map<String, Class> map = new HashMap<>();

    @Override
    public Object postProcessBeforeInitialization(final Object o, final String s) throws BeansException {
        Class<?> originalClass = o.getClass();

        if (originalClass.isAnnotationPresent(Transaction.class)) {
            map.put(s, originalClass);
        }

        return o;
    }

    @Override
    public Object postProcessAfterInitialization(final Object o, final String s) throws BeansException {
        Class<?> originalClass = o.getClass();
        if (map.containsKey(s)) {
            return Proxy.newProxyInstance(originalClass.getClassLoader(), originalClass.getInterfaces(),
                    invocationHandler(o));
        }
        return o;
    }

    private InvocationHandler invocationHandler(Object o) {
        return (proxy, method, args) -> {
            System.out.println("transaction begin");
            method.invoke(o, args);
            System.out.println("transaction end");
            return o;
        };
    }
}
