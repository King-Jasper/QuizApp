package com.quizapp.quiz.core.exception.advice;

import com.quizapp.quiz.core.exception.*;
import com.quizapp.quiz.core.utils.Constants;
import com.quizapp.quiz.model.payload.base.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ControllerAdvice
@Slf4j
public class GlobalControllerAdviceHandler {

    @ResponseStatus(BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(InvalidCredentialsException.class)
    public BaseResponse<?> handleException(InvalidCredentialsException exception) {
        log.error(exception.getMessage(), exception);
        return BaseResponse.builder().responseCode(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .responseMessage(exception.getMessage()).timeStamp(LocalDateTime.now())
                .build();
    }

    @ResponseBody
    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public BaseResponse<?> handleException(Exception exception) {
        log.error(exception.getMessage(), exception);
        return BaseResponse.builder().responseCode(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .responseMessage(Constants.INTERNAL_SERVER_ERROR).timeStamp(LocalDateTime.now())
                .build();
    }

    @ResponseStatus(BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(IllegalArgumentException.class)
    public BaseResponse<?> handleException(IllegalArgumentException exception) {
        log.error(exception.getMessage(), exception);
        return BaseResponse.builder().responseCode(HttpStatus.BAD_REQUEST.toString())
                .responseMessage(exception.getMessage()).timeStamp(LocalDateTime.now())
                .build();
    }

    @ResponseStatus(BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(UserNotFoundException.class)
    public BaseResponse<?> handleException(UserNotFoundException exception) {
        log.error(exception.getMessage(), exception);
        return BaseResponse.builder().responseCode(HttpStatus.BAD_REQUEST.toString())
                .responseMessage(exception.getMessage()).timeStamp(LocalDateTime.now())
                .build();
    }

    @ResponseStatus(BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(NoQuizAttemptsFoundException.class)
    public BaseResponse<?> handleException(NoQuizAttemptsFoundException exception) {
        log.error(exception.getMessage(), exception);
        return BaseResponse.builder().responseCode(HttpStatus.BAD_REQUEST.toString())
                .responseMessage(exception.getMessage()).timeStamp(LocalDateTime.now())
                .build();
    }

    @ResponseStatus(BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(QuizNotFoundException.class)
    public BaseResponse<?> handleException(QuizNotFoundException exception) {
        log.error(exception.getMessage(), exception);
        return BaseResponse.builder().responseCode(HttpStatus.BAD_REQUEST.toString())
                .responseMessage(exception.getMessage()).timeStamp(LocalDateTime.now())
                .build();
    }


    @ResponseStatus(BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(InvalidRequestException.class)
    public BaseResponse<?> handleException(InvalidRequestException exception) {
        log.error(exception.getMessage(), exception);
        return BaseResponse.builder().responseCode(HttpStatus.BAD_REQUEST.toString())
                .responseMessage(exception.getMessage()).timeStamp(LocalDateTime.now())
                .build();
    }

    @ResponseStatus(BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(UsernameNotFoundException.class)
    public BaseResponse<?> handleException(UsernameNotFoundException exception) {
        log.error(exception.getMessage(), exception);
        return BaseResponse.builder().responseCode(HttpStatus.BAD_REQUEST.toString())
                .responseMessage(exception.getMessage()).timeStamp(LocalDateTime.now())
                .build();
    }

    @ResponseStatus(BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(GenericException.class)
    public BaseResponse<?> handleException(GenericException exception) {
        log.error(exception.getMessage(), exception);
        return BaseResponse.builder().responseCode(HttpStatus.BAD_REQUEST.toString())
                .responseMessage(exception.getMessage()).timeStamp(LocalDateTime.now())
                .build();
    }

    @ResponseStatus(BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public BaseResponse<?> handleException(MethodArgumentNotValidException exception) {
        log.error(exception.getMessage(), exception);
        String errorMessage = exception.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> fieldError.getField() + " " + fieldError.getDefaultMessage())
                .collect(Collectors.joining(";"));
        log.error(exception.getMessage(), exception);
        return BaseResponse.builder().responseMessage(errorMessage).responseCode(BAD_REQUEST.toString()).timeStamp(LocalDateTime.now()).build();
    }
}
