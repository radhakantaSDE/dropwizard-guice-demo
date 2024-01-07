package com.learn.app.resources;

import com.codahale.metrics.annotation.Timed;
import com.learn.app.repository.dao.UserDao;
import com.learn.app.service.HelloWorldService;
import com.learn.app.service.Service2;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.jdbi.v3.core.Jdbi;

@Path("/greet")
@Produces(MediaType.APPLICATION_JSON)
public class HelloWorldResource {

    private Jdbi jdbi;
    private HelloWorldService helloWorldService;
    private Service2 service2;

    public HelloWorldResource() {}

    @Inject
    public HelloWorldResource(Jdbi jdbi, HelloWorldService helloWorldService, Service2 service2) {
        this.jdbi = jdbi;
        this.helloWorldService = helloWorldService;
        this.service2 = service2;
    }

    @GET
    @Timed
    public String sayHello() {
        return "Hello DropWizard!!!";
    }

    @GET
    @Path("/student/{id}")
    public String student(@PathParam("id") int id) {
        System.out.println("Recieved...");

        helloWorldService.testService();
        service2.test();

        String name= jdbi.onDemand(UserDao.class).findNameById(1);
        System.out.println("Done= " + name);

        return name;
    }
}
