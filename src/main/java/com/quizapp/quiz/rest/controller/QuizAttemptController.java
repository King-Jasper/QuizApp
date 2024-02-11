package com.quizapp.quiz.rest.controller;

import com.quizapp.quiz.model.payload.base.BaseResponse;
import com.quizapp.quiz.model.payload.quiz.QuestionAnswerRequest;
import com.quizapp.quiz.rest.service.QuizAttemptService;
import com.quizapp.quiz.rest.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/quiz-attempts")
public class QuizAttemptController {

    private final QuizService quizService;

    private final QuizAttemptService quizAttemptService;


    @PostMapping(value = "/submit", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<?> submitQuizAttempt(@RequestParam Long userId, @RequestParam Long quizId,
                                          @RequestBody List<QuestionAnswerRequest> submittedAnswers) {
       return quizService.submitQuizAttempt(userId, quizId, submittedAnswers);
    }

    @GetMapping(value = "/user/{userId}/")
    public BaseResponse<?> getUserQuizAttempts(@PathVariable Long userId, @RequestParam(defaultValue = "0") int pageNumber,
                                               @RequestParam(defaultValue = "10") int pageSize) {
        return quizAttemptService.fetchUserQuizAttempts(userId, pageNumber, pageSize);
    }
}
