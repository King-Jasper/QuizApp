package com.quizapp.quiz.model.payload.quiz;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.quizapp.quiz.model.payload.user.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class QuizAttemptResponse implements Serializable {

    @JsonProperty("quiz_title")
    private String quizTitle;

    @JsonProperty("quiz_score")
    private int quizScore;

    @JsonProperty("player")
    private UserDTO player;

    @JsonProperty("created_at")
    private String createdAt;
}
