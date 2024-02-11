package com.quizapp.quiz.core.exception;

public class QuizNotFoundException extends RuntimeException {
    public QuizNotFoundException() {
    }

    public QuizNotFoundException(String message) {
        super(message);
    }

    public QuizNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
