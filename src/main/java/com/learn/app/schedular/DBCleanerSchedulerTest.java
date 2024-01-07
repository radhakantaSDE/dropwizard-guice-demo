package com.learn.app.schedular;

import com.google.common.util.concurrent.AbstractScheduledService;
import com.google.inject.Inject;
import com.learn.app.service.Service2;

import java.util.concurrent.TimeUnit;

public class DBCleanerSchedulerTest extends AbstractScheduledService {

    private Service2 service2;

    @Inject
    public DBCleanerSchedulerTest(Service2 service2) {
        this.service2 = service2;
    }

    @Override
    protected void runOneIteration() throws Exception {
        System.out.println("Job is running : "+service2.test());
    }

    @Override
    protected Scheduler scheduler() {
        return AbstractScheduledService.Scheduler.newFixedRateSchedule(0, 3, TimeUnit.SECONDS);
    }
}
