package com.quizapp.quiz.rest.controller;

import com.quizapp.quiz.model.payload.base.BaseResponse;
import com.quizapp.quiz.model.payload.user.LoginRequest;
import com.quizapp.quiz.model.payload.user.SignUpRequest;
import com.quizapp.quiz.rest.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/users")
public class UserController {
    private final UserService userService;

    @PostMapping(value = "/signup", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<?> registerUser(@RequestBody @Valid SignUpRequest signUpRequest) {
        return userService.handleUserRegistration(signUpRequest);
    }

    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<?> loginUser(@RequestBody @Valid LoginRequest loginRequest) {
        return userService.handleUserLogin(loginRequest);
    }
}
