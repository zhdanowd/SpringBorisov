package com.zhdanov.bpp;

import java.util.Arrays;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;


public class DestroyMethodBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(final ConfigurableListableBeanFactory configurableListableBeanFactory) throws
            BeansException {
        String[] beanDefinitionNames = configurableListableBeanFactory.getBeanDefinitionNames();
        Arrays.stream(beanDefinitionNames)
                .map(beanDefinitionName -> (AbstractBeanDefinition) configurableListableBeanFactory.getBeanDefinition
                        (beanDefinitionName))
                .filter(AbstractBeanDefinition::isPrototype)
                .forEach(abstractBeanDefinition -> {
                    if (abstractBeanDefinition.getDestroyMethodName() != null) {
                        System.out.println(abstractBeanDefinition.getBeanClassName() + " is prototype and has destroy" +
                                " " +
                                "method");
                    }
                });
    }
}
