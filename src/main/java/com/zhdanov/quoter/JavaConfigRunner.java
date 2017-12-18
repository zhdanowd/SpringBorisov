package com.zhdanov.quoter;

import com.zhdanov.quoter.quoter.Quoter;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class JavaConfigRunner {
    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext("com.zhdanov.quoter");
        Quoter terminator = (Quoter)context.getBean("terminator");
        terminator.sayQuote();
    }
}
