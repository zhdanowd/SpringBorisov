package com.zhdanov.bpp;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;

import java.util.Optional;
import java.util.stream.Stream;

public class MyDeprecatedBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        Stream.of(configurableListableBeanFactory.getBeanDefinitionNames()).forEach(System.out::println);
        String[] beanDefinitionNames = configurableListableBeanFactory.getBeanDefinitionNames();

        Stream.of(beanDefinitionNames)
                .map(beanDefinitionName -> (AbstractBeanDefinition) configurableListableBeanFactory.getBeanDefinition(beanDefinitionName))
                .forEach(beanDefinition -> {
                    String originalClassName = beanDefinition.getBeanClassName();
                    Class<?> originalClass = null;
                    try {
                        originalClass = Class.forName(originalClassName);
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    Optional.ofNullable(originalClass.getAnnotation(MyDeprecated.class))
                            .ifPresent(annotation -> beanDefinition.setBeanClass(annotation.newClass()));

                });
    }
}
