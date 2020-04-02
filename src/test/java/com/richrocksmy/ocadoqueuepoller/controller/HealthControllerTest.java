package com.richrocksmy.ocadoqueuepoller.controller;

import io.micronaut.http.client.RxHttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.annotation.MicronautTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static org.assertj.core.api.Assertions.assertThat;

@MicronautTest
public final class HealthControllerTest {

    @Inject
    @Client("/")
    private  RxHttpClient client;

    @Test
    public void healthShouldReturn200AndMessage() {
        String response = client.toBlocking().retrieve("/health");

        assertThat(response).isNotNull();
        assertThat(response).contains("Hello, world");
    }
}

