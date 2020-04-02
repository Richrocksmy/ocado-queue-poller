package com.richrocksmy.ocadoqueuepoller.controller;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Produces;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller("/health")
public class HealthController {

    @Get
    @Produces(MediaType.TEXT_PLAIN)
    public HttpResponse<String> health() {
        log.debug("Recieved GET request to /health.");
        return HttpResponse.ok("Hello, world");
    }
}