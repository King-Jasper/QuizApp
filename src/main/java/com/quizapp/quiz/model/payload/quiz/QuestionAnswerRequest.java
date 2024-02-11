package com.quizapp.quiz.model.payload.quiz;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class QuestionAnswerRequest {

    @NotEmpty(message = "question id must not be null")
    @JsonProperty("question_id")
    private Long questionId;

    @NotEmpty(message = "submitted answer must not be null")
    @JsonProperty("submitted_answer")
    private String submittedAnswer;
}
