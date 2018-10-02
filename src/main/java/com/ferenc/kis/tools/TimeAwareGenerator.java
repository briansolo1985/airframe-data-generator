package com.ferenc.kis.tools;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicLong;

import static java.lang.Thread.sleep;

public abstract class TimeAwareGenerator implements ClockSubscriber, Runnable {

    private static final int ONE_SEC_IN_MILLISEC = 1000;

    protected final long sleepMillis;
    private final AtomicLong currentTime;
    private final BlockingQueue<? super String> outputBuffer;

    private boolean isStopped;

    public TimeAwareGenerator(int recordsPerSec, double timeRate, BlockingQueue<? super String> outputBuffer) {
        this.sleepMillis = (long) ((ONE_SEC_IN_MILLISEC / timeRate) / recordsPerSec);
        this.currentTime = new AtomicLong();
        this.isStopped = false;
        this.outputBuffer = outputBuffer;
    }

    @Override
    public void tickCallback(long nowEpochMillis) {
        currentTime.set(nowEpochMillis);
    }

    @Override
    public void run() {
        while(!isStopped) {
            try {
                String record = execute(currentTime.get());
                outputBuffer.put(record);
                sleep(sleepMillis);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                isStopped = true;
            }
        }

    }

    public void stop() {
        isStopped = true;
    }

    protected abstract String execute(long currentTime);
}
