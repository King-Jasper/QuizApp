package com.quizapp.quiz.model.payload.quiz;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestionDTO {

    @JsonProperty(value = "question_text")
    private String questionText;

    @JsonProperty(value = "answer_options")
    private List<String> answerOptions;

    @JsonProperty(value = "subject_category")
    private String subjectCategory;
}
