package com.zhdanov.quoter;

import com.zhdanov.quoter.quoter.Quoter;
import com.zhdanov.quoter.quoter.TalkingRobot;
import com.zhdanov.quoter.quoter.TalkingRobotImpl;
import com.zhdanov.quoter.quoter.Terminator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class Config {

    @Bean
    public Quoter terminator() {
        Terminator terminator = new Terminator();

        List<String> messages = new ArrayList<>();
        messages.add("Trust me");
        terminator.setMessages(messages);

        System.out.println(terminator.getMessages());
        return terminator;
    }

    @Bean
    public TalkingRobot talkingRobot(){
        TalkingRobotImpl robot = new TalkingRobotImpl();
        List<Quoter> quoters = new ArrayList<>();
        quoters.add(terminator());
        robot.setQuoters(quoters);

        return robot;
    }

}
