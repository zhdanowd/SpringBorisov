package com.zhdanov.colors;

import org.springframework.beans.factory.FactoryBean;

import java.awt.*;
import java.util.Random;

public class ColorFactory implements FactoryBean<Color> {

    @Override
    public Color getObject() {
        Random random = new Random();
        return new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255));
    }

    @Override
    public Class<?> getObjectType() {
        return Color.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
}
