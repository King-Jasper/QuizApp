package com.quizapp.quiz.model.payload.quiz;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.quizapp.quiz.core.utils.enums.EnumValue;
import com.quizapp.quiz.model.enums.QuizDifficultyLevel;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class QuizSubmissionRequest implements Serializable {
    @NotEmpty(message = "quiz title must not be null")
    @JsonProperty("quiz_title")
    private String title;

    @NotEmpty(message = "quiz description must not be null")
    @JsonProperty("quiz_description")
    private String description;

    @NotEmpty(message = "score per answer must not be null")
    @JsonProperty("score_per_answer")
    private String scorePerCorrectAnswer;

    @NotEmpty(message = "quiz_type must not be null")
    @JsonProperty("quiz_type")
    @EnumValue(enumClass = QuizDifficultyLevel.class, message = "invalid quiz type")
    private String quizDifficultyLevel;

    private List<QuizQuestionMetaData> questionMetaData;

    @Getter
    @Setter
    @Data
    public static class QuizQuestionMetaData {
        @NotEmpty(message = "question_text must not be null")
        @JsonProperty("question_text")
        private String questionText;

        @NotEmpty(message = "correct_answer must not be null")
        @JsonProperty("correct_answer")
        private String correctAnswer;

        @NotEmpty(message = "answer_options must not be null")
        @JsonProperty("answer_options")
        private String answerOptions;

        @NotEmpty(message = "question subject category must not be null")
        @JsonProperty("subject_category")
        private String subjectCategory;
    }
}
