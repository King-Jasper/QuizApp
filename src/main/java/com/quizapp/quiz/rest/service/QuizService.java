package com.quizapp.quiz.rest.service;

import com.quizapp.quiz.model.payload.base.BaseResponse;
import com.quizapp.quiz.model.payload.quiz.QuestionAnswerRequest;
import com.quizapp.quiz.model.payload.quiz.QuizSubmissionRequest;

import java.security.Principal;
import java.util.List;

public interface QuizService {
    BaseResponse<?> handleQuizSubmission(QuizSubmissionRequest quizSubmissionRequest, Principal principal);

    BaseResponse<?> submitQuizAttempt(Long userId, Long quizId, List<QuestionAnswerRequest> submittedAnswers);

    BaseResponse<?> fetchQuizzes(int pageNumber, int pageSize, Principal principal);
}
