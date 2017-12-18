package com.zhdanov.reflection;

import java.lang.reflect.InvocationTargetException;

public class Main {

    public static void main(String[] args) throws IllegalAccessException, InvocationTargetException,
            InstantiationException {
        ObjectFactory.createObject(Person.class);
    }
}
