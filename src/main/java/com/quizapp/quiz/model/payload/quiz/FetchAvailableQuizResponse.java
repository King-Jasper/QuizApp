package com.quizapp.quiz.model.payload.quiz;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FetchAvailableQuizResponse {

    @JsonProperty(value = "quiz_title")
    private String title;

    @JsonProperty(value = "description")
    private String description;

    @JsonProperty(value = "mark_per_question")
    private Integer markPerQuestion;

    @JsonProperty(value = "quiz_type")
    private String quizDifficultyLevel;

    @JsonProperty(value = "quiz_questions")
    private List<QuestionDTO> questions;
}
