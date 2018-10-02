package com.ferenc.kis.tools;

import java.util.concurrent.BlockingQueue;

public class SimpleGenerator extends TimeAwareGenerator {

    private static final String TEMPLATE = "simple.metric 1 %d";

    public SimpleGenerator(int recordsPerSec, double timeRate, BlockingQueue<? super String> outputBuffer) {
        super(recordsPerSec, timeRate, outputBuffer);
    }

    // TODO generate random delta for timestamps to simulate data lateness
    @Override
    protected String execute(long currentTime) {
        String record = String.format(TEMPLATE, currentTime);
        return record;
    }


}
