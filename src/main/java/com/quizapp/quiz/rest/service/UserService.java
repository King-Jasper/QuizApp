package com.quizapp.quiz.rest.service;

import com.quizapp.quiz.model.payload.base.BaseResponse;
import com.quizapp.quiz.model.payload.user.LoginRequest;
import com.quizapp.quiz.model.payload.user.SignUpRequest;

public interface UserService {
    BaseResponse<?> handleUserRegistration(SignUpRequest signUpRequest);

    BaseResponse<?> handleUserLogin(LoginRequest loginRequest);
}
