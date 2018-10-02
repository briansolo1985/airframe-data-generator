package com.ferenc.kis.tools;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class Main {



    public static void main(String[] args) throws InterruptedException {
        String from = "2018-09-10T10:00:00.00Z";
        String to = "2018-09-10T10:02:00.00Z";
        double timeRate = 1.0;
        int recordsPerSec = 5;

        BlockingQueue<String> buffer = new LinkedBlockingDeque<>();

        TimeAwareRecordProducer timeAwareGeneratorController = new TimeAwareRecordProducer(from, to, timeRate, recordsPerSec, buffer);


    }
}
