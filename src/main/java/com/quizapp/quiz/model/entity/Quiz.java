package com.quizapp.quiz.model.entity;

import com.quizapp.quiz.model.enums.QuizDifficultyLevel;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Table(name = "quizzes_tbl")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Quiz extends BaseEntity {

    @Column(name = "quiz_title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "mark_per_question")
    private Integer markPerQuestion;

    @Column(name = "quiz_type")
    @Enumerated(value = EnumType.STRING)
    private QuizDifficultyLevel quizDifficultyLevel;

    @OneToMany(fetch = FetchType.LAZY, targetEntity = Question.class)
    private List<Question> questions;

    @OneToMany(fetch = FetchType.LAZY, targetEntity = QuizAttempt.class)
    private List<QuizAttempt> quizAttempts;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = AppUser.class)
    private AppUser user;

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof Quiz && ((Quiz)obj).getId().equals(this.getId()));
    }
}
