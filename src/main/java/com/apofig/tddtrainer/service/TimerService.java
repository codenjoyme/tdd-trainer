package com.apofig.tddtrainer.service;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * User: sanja
 * Date: 26.01.14
 * Time: 21:26
 */
public class TimerService implements Runnable {

    private ScheduledThreadPoolExecutor executor;
    private ScheduledFuture<?> future;

    private volatile boolean paused;
    private long period;

    private PlayerServiceImpl service;

    public TimerService() {
        period = 2 * 1000;
        paused = false;
        service = new PlayerServiceImpl();
    }

    public void init() {
        executor = new ScheduledThreadPoolExecutor(1);
        future = executor.scheduleAtFixedRate(this, period, period, TimeUnit.MILLISECONDS);
    }

    @Override
    public void run() {
        if (paused) {
            return;
        }

        System.out.println("------------------ tick -----------------");
        try {
            service.tick();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new TimerService().init();
    }

    public void pause() {
        this.paused = true;
    }

    public void resume() {
        this.paused = false;
    }

    public boolean isPaused() {
        return this.paused;
    }

    public void setPeriod(int period) {
        this.period = period;
    }
}