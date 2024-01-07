package com.learn.app.config;

import com.google.common.util.concurrent.AbstractScheduledService;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.multibindings.MapBinder;
import com.google.inject.multibindings.Multibinder;
import com.google.inject.name.Names;
import com.learn.app.schedular.DBCleanerSchedulerTest;
import com.learn.app.schedular.PriceSetupScheduler;
import com.learn.app.service.HelloWorldService;
import com.learn.app.service.Service2;
import io.dropwizard.core.setup.Environment;
import io.dropwizard.jdbi3.JdbiFactory;
import org.jdbi.v3.core.Jdbi;


public class DropwizardDemoModule extends AbstractModule {

    private Environment environment;
    private HelloWorldConfiguration helloWorldConfiguration;

    public DropwizardDemoModule(Environment environment, HelloWorldConfiguration helloWorldConfiguration) {
        this.helloWorldConfiguration = helloWorldConfiguration;
        this.environment = environment;
    }

    @Override
    protected void configure() {

        // Service instance
        bind(HelloWorldService.class).in(Singleton.class);

        // Multi-binding Schedulers : https://github.com/google/guice/wiki/Multibindings
        Multibinder<AbstractScheduledService> schedulers = Multibinder.newSetBinder(binder(), AbstractScheduledService.class);
        schedulers.addBinding().to(DBCleanerSchedulerTest.class).in(Singleton.class);
        schedulers.addBinding().to(PriceSetupScheduler.class).in(Singleton.class);

        // Named-Binding : https://github.com/google/guice/wiki/Multibindings
//        bind(AbstractScheduledService.class).annotatedWith(Names.named("dbCleanerScheduler")).to(DBCleanerSchedulerTest.class);
//        bind(AbstractScheduledService.class).annotatedWith(Names.named("priceScheduler")).to(PriceSetupScheduler.class);

        // Mapped-Binding : https://github.com/google/guice/wiki/Multibindings
        MapBinder<String, AbstractScheduledService> schedulerMappedBinder = MapBinder.newMapBinder(binder(), String.class, AbstractScheduledService.class);
        schedulerMappedBinder.addBinding("dbCleanerScheduler").to(DBCleanerSchedulerTest.class);
        schedulerMappedBinder.addBinding("priceScheduler").to(PriceSetupScheduler.class);
    }

    @Provides
    @Singleton
    public Service2 service2() {

        return new Service2();
    }

    @Provides
    @Singleton
    public Jdbi jdbi() {
        final JdbiFactory factory = new JdbiFactory();

        return factory.build(environment, helloWorldConfiguration.getDataSourceFactory(), "mysql");
    }
}
