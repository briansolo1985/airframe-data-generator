package com.ferenc.kis.tools;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class Clock implements Runnable {

    private static final double NORMAL_RATE = 1.0;
    private static final int ONE_SEC_IN_MILLISEC = 1000;

    private final long from;
    private final long to;
    private final double timeRate;

    private final AtomicLong currentTimestamp;

    private final List<ClockSubscriber> subscribers;

    // 2007-12-03T10:15:30.00Z
    public Clock(String from, String to, List<ClockSubscriber> subscribers, double timeRate) {
        this.from = Instant.parse(from).toEpochMilli();
        this.to = Instant.parse(to).toEpochMilli();
        this.timeRate = timeRate;
        this.currentTimestamp = new AtomicLong(this.from);
        this.subscribers = new ArrayList<>(subscribers);
    }

    public Clock(String from, String to, List<ClockSubscriber> subscribers) {
        this(from, to, subscribers, NORMAL_RATE);
    }

    @Override
    public void run() {
        int realRate = (int) (ONE_SEC_IN_MILLISEC / timeRate);
        try {
            while(currentTimestamp.get() < to) {
                Thread.sleep(realRate);
                subscribers.stream().forEach(s -> s.tickCallback(currentTimestamp.addAndGet(ONE_SEC_IN_MILLISEC)));
            }
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }
    }
}
