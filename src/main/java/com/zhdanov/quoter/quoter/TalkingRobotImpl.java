package com.zhdanov.quoter.quoter;

import java.util.List;

import javax.annotation.PostConstruct;

import com.zhdanov.bpp.Benchmark;
import com.zhdanov.bpp.RunThisMethod;
import com.zhdanov.bpp.Transaction;

@Transaction
public class TalkingRobotImpl implements TalkingRobot {

    private List<Quoter> quoters;

    @Benchmark
    @Override
    public void talk() {
        quoters.stream().forEach(Quoter::sayQuote);
    }

    public void setQuoters(List quoters) {
        this.quoters = quoters;
    }
}
