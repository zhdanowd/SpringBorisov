package com.zhdanov.quoter;

import com.zhdanov.quoter.quoter.TalkingRobot;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class XmlRunner {
    public static void main(String[] args) throws InterruptedException {

        ApplicationContext context = new ClassPathXmlApplicationContext("quoterContext.xml");

        context.getBean("talkingRobot", TalkingRobot.class).talk();

        context.getBean("prototypeBean");

    }
}
