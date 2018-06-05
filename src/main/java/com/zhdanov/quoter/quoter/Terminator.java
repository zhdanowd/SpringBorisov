package com.zhdanov.quoter.quoter;

import com.zhdanov.bpp.MyDeprecated;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@MyDeprecated(newClass = T1000.class)
public class Terminator implements Quoter {

    private List<String> messages;

    @Autowired
    private TalkingRobot talkingRobot;

    @Override
    public void sayQuote() {
        messages.stream().forEach(System.out::println);
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }

    public List<String> getMessages() {
        return messages;
    }

    public TalkingRobot getTalkingRobot(){
        return talkingRobot;
    }
}
