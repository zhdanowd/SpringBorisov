package com.zhdanov.quoter;

import com.zhdanov.quoter.quoter.Quoter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.stream.Stream;

public class JavaConfigRunner {
    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext("com.zhdanov");
        Quoter terminator = (Quoter)context.getBean("terminator");
        terminator.sayQuote();
        Stream.of(context.getBeanDefinitionNames()).forEach(System.out::println);
    }
}
