package com.quizapp.quiz.model.entity;

import com.quizapp.quiz.model.enums.QuizDifficultyLevel;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "questions")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Question extends BaseEntity {

    @Column(name = "question_text")
    private String questionText;

    @Column(name = "correct_answer")
    private String correctAnswer;

    @Column(name = "answer_options", columnDefinition = "TEXT")
    private String answerOptions;

    @Column(name = "subject_category")
    private String subjectCategory;

    @ManyToOne
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof Question && ((Question)obj).getId().equals(this.getId()));
    }
}
