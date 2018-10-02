package com.ferenc.kis.tools;

import java.util.Arrays;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TimeAwareRecordProducer {

    private final Clock clock;
    private final TimeAwareGenerator timeAwareGenerator;

    private final ExecutorService executorService;


    public TimeAwareRecordProducer(String from, String to, double timeRate, int recordsPerSec, BlockingQueue<? super String> outputBuffer) {
        this.timeAwareGenerator = new SimpleGenerator(recordsPerSec, timeRate, outputBuffer);
        this.clock = new Clock(from, to, Arrays.asList(this.timeAwareGenerator), timeRate);
        this.executorService = Executors.newFixedThreadPool(2);
    }

    public void start() {
        executorService.submit(clock);
        executorService.submit(timeAwareGenerator);
    }

    public void stop() {
        executorService.shutdownNow();
    }
}
