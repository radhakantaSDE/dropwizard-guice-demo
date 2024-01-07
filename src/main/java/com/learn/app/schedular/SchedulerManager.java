package com.learn.app.schedular;

import com.google.common.util.concurrent.AbstractScheduledService;
import com.google.inject.Inject;
import io.dropwizard.lifecycle.Managed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

public class SchedulerManager implements Managed {

    private final Logger LOGGER = LoggerFactory.getLogger(SchedulerManager.class);
    private final Set<AbstractScheduledService> schedulers;

    @Inject
    public SchedulerManager(Set<AbstractScheduledService> schedulers) {
        LOGGER.info("Creating {} schedulers", schedulers.size());
        this.schedulers = schedulers;
    }

    @Override
    public void start() throws Exception {

        for (AbstractScheduledService scheduler : schedulers) {
            scheduler.startAsync().awaitRunning();
        }
    }

    @Override
    public void stop() throws Exception {
        for (AbstractScheduledService scheduler : schedulers) {
            scheduler.stopAsync().awaitTerminated();
        }
    }

}
