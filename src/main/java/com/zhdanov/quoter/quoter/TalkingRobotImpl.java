package com.zhdanov.quoter.quoter;

import com.zhdanov.bpp.Benchmark;
import com.zhdanov.bpp.Transaction;

import java.util.List;

public class TalkingRobotImpl implements TalkingRobot {

    private List<Quoter> quoters;

    @Transaction
    @Benchmark
    @Override
    public void talk() {
        quoters.stream().forEach(Quoter::sayQuote);
    }

    @Transaction
    public void anotherTalk(){
        System.out.println("hello");
    }

    public void setQuoters(List quoters) {
        this.quoters = quoters;
    }
}
