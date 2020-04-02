package com.richrocksmy.ocadoqueuepoller.controller;

import com.richrocksmy.ocadoqueuepoller.model.request.UserRequest;
import com.richrocksmy.ocadoqueuepoller.model.response.UserResponse;
import com.richrocksmy.ocadoqueuepoller.service.UserService;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.client.RxHttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.annotation.MicronautTest;
import io.micronaut.test.annotation.MockBean;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@MicronautTest
public final class UserControllerTest {

    private static final String USERS_ENDPOINT = "/users";

    @Inject
    private UserService userService;

    @Inject
    @Client("/")
    private  RxHttpClient client;

    @MockBean(UserService.class)
    public UserService dependency() {
        return mock(UserService.class);
    }

    @Test
    public void shouldCreateUserWhenUserDoesNotExist() {
        // Given
        String userEmail = "person@domain.com";
        when(userService.addUser(userEmail)).thenReturn(userEmail);

        UserRequest userRequest = UserRequest.builder().email(userEmail).build();

        // When
        HttpRequest<UserRequest> request = HttpRequest.POST(USERS_ENDPOINT, userRequest);
        HttpResponse<UserResponse> response = client.toBlocking().exchange(request, UserResponse.class);

        // Then
        assertThat(response.getStatus().getCode()).isEqualTo(HttpStatus.OK.getCode());
        assertThat(response.body().getEmail()).isEqualTo(userEmail);

        verify(userService).addUser(userEmail);
    }

    public void shouldReturnConflictWhenUserAlreadyExists() {
        String response = client.toBlocking().retrieve(USERS_ENDPOINT);

    }

    @Test
    public void shouldDeleteUserWhenTheyExist() {
        // Given
        String userEmail = "person@domain.com";
        when(userService.deleteUser(userEmail)).thenReturn(userEmail);

        UserRequest userRequest = UserRequest.builder().email(userEmail).build();

        // When
        HttpRequest<UserRequest> request = HttpRequest.DELETE(USERS_ENDPOINT, userRequest);
        HttpResponse<UserResponse> response = client.toBlocking().exchange(request, UserResponse.class);

        // Then
        assertThat(response.getStatus().getCode()).isEqualTo(HttpStatus.OK.getCode());
        assertThat(response.body().getEmail()).isEqualTo(userEmail);

        verify(userService).deleteUser(userEmail);
    }

    @Test
    public void shouldNotThrowExceptionWhenUnknownUserIsDeleted() {
        // Given
        String userEmail = "person@domain.com";
        when(userService.deleteUser(userEmail)).thenReturn(userEmail);

        UserRequest userRequest = UserRequest.builder().email(userEmail).build();

        // When
        HttpRequest<UserRequest> request = HttpRequest.DELETE(USERS_ENDPOINT, userRequest);
        HttpResponse<UserResponse> response = client.toBlocking().exchange(request, UserResponse.class);

        // Then
        assertThat(response.getStatus().getCode()).isEqualTo(HttpStatus.OK.getCode());
        assertThat(response.body().getEmail()).isEqualTo(userEmail);

        verify(userService).deleteUser(userEmail);
    }
}

