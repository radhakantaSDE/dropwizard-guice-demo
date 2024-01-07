package com.learn.app.schedular;

import com.google.common.util.concurrent.AbstractScheduledService;

import java.util.concurrent.TimeUnit;

public class PriceSetupScheduler extends AbstractScheduledService {
    @Override
    protected void runOneIteration() throws Exception {
        System.out.println("Scheduler : Setting up prices");
    }

    @Override
    protected Scheduler scheduler() {
        return AbstractScheduledService.Scheduler.newFixedRateSchedule(0, 3, TimeUnit.SECONDS);
    }
}
