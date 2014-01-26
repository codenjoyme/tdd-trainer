package com.apofig.tddtrainer.service;

import org.springframework.beans.factory.annotation.Autowired;

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

    private long period;

    @Autowired
    private PlayerServiceImpl service;

    public void init() {
        executor = new ScheduledThreadPoolExecutor(1);
        future = executor.scheduleAtFixedRate(this, period, period, TimeUnit.MILLISECONDS);
    }

    @Override
    public void run() {
        System.out.println("------------------ tick -----------------");
        try {
            service.tick();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setPeriod(int period) {
        this.period = period;
    }
}