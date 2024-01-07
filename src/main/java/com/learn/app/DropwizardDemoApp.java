package com.learn.app;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.learn.app.config.DropwizardDemoModule;
import com.learn.app.config.HelloWorldConfiguration;
import com.learn.app.resources.HelloWorldResource;
import com.learn.app.schedular.SchedulerManager;
import io.dropwizard.core.Application;
import io.dropwizard.core.setup.Bootstrap;
import io.dropwizard.core.setup.Environment;

public class DropwizardDemoApp extends Application<HelloWorldConfiguration> {

    public static void main(String[] args) throws Exception {
        args = new String[]{"server", "./config/simple-config.yaml"};
        new DropwizardDemoApp().run(args);
    }

    @Override
    public String getName() {
        return "hello-world";
    }

    @Override
    public void initialize(Bootstrap<HelloWorldConfiguration> bootstrap) {

    }

    @Override
    public void run(HelloWorldConfiguration configuration, Environment environment) {

        Injector injector = Guice.createInjector(new DropwizardDemoModule(environment, configuration));

        // Register instances
        environment.jersey().register(injector.getInstance(HelloWorldResource.class));

        environment.lifecycle().manage(injector.getInstance(SchedulerManager.class));
    }
}
