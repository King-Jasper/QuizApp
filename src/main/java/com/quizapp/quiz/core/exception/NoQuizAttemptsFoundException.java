package com.quizapp.quiz.core.exception;

public class NoQuizAttemptsFoundException extends RuntimeException {
    public NoQuizAttemptsFoundException() {
    }

    public NoQuizAttemptsFoundException(String message) {
        super(message);
    }

    public NoQuizAttemptsFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
