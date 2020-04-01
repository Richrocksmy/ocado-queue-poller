package com.richrocksmy.ocadoqueuepoller;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Produces;

@Controller()
public class GreetingController {

    @Produces(MediaType.TEXT_PLAIN)
    @Get
    public String index() {
        return "HEALTHY";
    }

    @Get("/hello")
    public String hello() {
        return "Hello World";
    }
}