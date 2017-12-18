package com.zhdanov.quoter.quoter;

import java.util.List;

import com.zhdanov.bpp.Benchmark;
import com.zhdanov.bpp.RunThisMethod;


public class Terminator implements Quoter {

    private List<String> messages;

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
}
