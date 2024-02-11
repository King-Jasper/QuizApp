package com.quizapp.quiz.rest.service;

import com.quizapp.quiz.model.payload.base.BaseResponse;

public interface QuizAttemptService {
    BaseResponse<?> fetchUserQuizAttempts(Long userId, int pageNumber, int pageSize);
}
