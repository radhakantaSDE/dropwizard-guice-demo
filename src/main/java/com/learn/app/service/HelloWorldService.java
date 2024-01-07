package com.learn.app.service;

import com.google.inject.Inject;
import jakarta.inject.Singleton;

@Singleton
public class HelloWorldService {

    private Service2 service2;

    @Inject
    public HelloWorldService(Service2 service2) {
        this.service2 = service2;
    }

    public String testService() {

        return "test";
    }
}
