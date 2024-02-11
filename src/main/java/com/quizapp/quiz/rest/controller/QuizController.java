package com.quizapp.quiz.rest.controller;

import com.quizapp.quiz.model.payload.base.BaseResponse;
import com.quizapp.quiz.model.payload.quiz.QuizSubmissionRequest;
import com.quizapp.quiz.rest.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/quiz")
public class QuizController {

    private final QuizService quizService;

    @PostMapping(value = "/submit", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<?> submitQuiz(@RequestBody QuizSubmissionRequest quizSubmissionRequest, Principal principal) {
        return quizService.handleQuizSubmission(quizSubmissionRequest, principal);
    }

    @GetMapping
    public BaseResponse<?> fetchQuizzes(@RequestParam(defaultValue = "0") int pageNumber,
                                        @RequestParam(defaultValue = "10") int pageSize, Principal principal) {
        return quizService.fetchQuizzes(pageNumber, pageSize, principal);
    }
}
