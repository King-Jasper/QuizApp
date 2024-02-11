package com.quizapp.quiz.model.payload.quiz;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.quizapp.quiz.model.payload.user.UserDTO;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class QuizScoreResponse implements Serializable {

    @JsonProperty("quiz_title")
    private String quizTitle;

    @JsonProperty("quiz_score")
    private int quizScore;

    @JsonProperty("quiz_player")
    private UserDTO player;
}
