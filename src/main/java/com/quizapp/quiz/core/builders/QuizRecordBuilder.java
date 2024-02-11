package com.quizapp.quiz.core.builders;

import com.quizapp.quiz.core.utils.enums.EnumUtil;
import com.quizapp.quiz.model.entity.AppUser;
import com.quizapp.quiz.model.entity.Question;
import com.quizapp.quiz.model.entity.Quiz;
import com.quizapp.quiz.model.entity.QuizAttempt;
import com.quizapp.quiz.model.enums.QuizDifficultyLevel;
import com.quizapp.quiz.model.payload.quiz.*;
import com.quizapp.quiz.model.payload.user.UserDTO;

import java.util.List;
import java.util.stream.Collectors;

public class QuizRecordBuilder {
    public static Quiz quizSubmissionToQuizRecord(QuizSubmissionRequest quizSubmissionRequest, AppUser loggedInUser) {
        return Quiz.builder().title(quizSubmissionRequest.getTitle()).user(loggedInUser).description(quizSubmissionRequest.getDescription())
                .quizDifficultyLevel(EnumUtil.convert(QuizDifficultyLevel.class, quizSubmissionRequest.getQuizDifficultyLevel())).markPerQuestion(Integer.valueOf(quizSubmissionRequest.getScorePerCorrectAnswer())).build();
    }

    public static Question resolveQuestionMetaDataToQuestion(QuizSubmissionRequest.QuizQuestionMetaData metaData) {
        return Question.builder()
                .questionText(metaData.getQuestionText())
                .correctAnswer(metaData.getCorrectAnswer())
                .answerOptions(metaData.getAnswerOptions())
                .subjectCategory(metaData.getSubjectCategory())
                .build();
    }

    public static QuizScoreResponse resolveQuizScoreResponse(int quizScore, String quizTitle, UserDTO player) {
       return QuizScoreResponse.builder().quizScore(quizScore).quizTitle(quizTitle)
                .player(player).build();
    }

    public static QuizAttemptResponse resolveQuizAttemptToQuizAttemptResponse(QuizAttempt quizAttempt) {
        UserDTO player = UserRecordBuilder.mapAppUserToUserDTO(quizAttempt.getUser());
        return QuizAttemptResponse.builder().quizScore(quizAttempt.getScore()).quizTitle(quizAttempt.getQuiz().getTitle())
                .createdAt(quizAttempt.getCreatedAt().toString()).player(player).build();
    }

    public static FetchAvailableQuizResponse resolveQuizToFetchAvailableQuizResponse(Quiz quiz) {
        return FetchAvailableQuizResponse.builder().title(quiz.getTitle()).description(quiz.getDescription())
                .markPerQuestion(quiz.getMarkPerQuestion()).quizDifficultyLevel(String.valueOf(EnumUtil.convert(QuizDifficultyLevel.class, quiz.getQuizDifficultyLevel().toString())))
                .questions(quiz.getQuestions().stream().map(QuizRecordBuilder::resolveQuestionEntityToQuestionDTO).collect(Collectors.toList())).build();
    }

    public static QuestionDTO resolveQuestionEntityToQuestionDTO(Question question) {
        return QuestionDTO.builder().questionText(question.getQuestionText()).subjectCategory(question.getSubjectCategory())
                .answerOptions(List.of(question.getAnswerOptions().split(","))).build();
    }
}
