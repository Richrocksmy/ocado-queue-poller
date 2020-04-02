package com.richrocksmy.ocadoqueuepoller.controller;

import com.richrocksmy.ocadoqueuepoller.model.request.UserRequest;
import com.richrocksmy.ocadoqueuepoller.model.response.UserResponse;
import com.richrocksmy.ocadoqueuepoller.service.UserService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Consumes;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Produces;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;

@Slf4j
@Controller("/users")
public class UserController {

    @Inject
    private UserService userService;

    @Post
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public HttpResponse registerUser(@Body final UserRequest userRequest) {
        log.debug("Received POST request to register user.");
        return HttpResponse.ok(UserResponse.builder().email(userService.addUser(userRequest.getEmail())).build());
    }

    @Delete
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public HttpResponse deleteUser(@Body final UserRequest userRequest) {
        log.debug("Received DELETE request to delete user.");
        return HttpResponse.ok(UserResponse.builder().email(userService.deleteUser(userRequest.getEmail())).build());
    }

}
