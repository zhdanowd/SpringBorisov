package com.zhdanov.reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class ObjectFactory {
    public static <T> T createObject(Class<T> t) throws IllegalAccessException, InstantiationException,
            InvocationTargetException {

        T object = t.newInstance();
        Method[] methods = t.getMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(RunThisMethod.class)) {
                method.invoke(object);
            }
        }

        return object;
    }

}
