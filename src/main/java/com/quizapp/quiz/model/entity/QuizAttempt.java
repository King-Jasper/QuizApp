package com.quizapp.quiz.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "quiz_attempts_tbl")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class QuizAttempt extends BaseEntity {

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = AppUser.class)
    @JoinColumn(name = "user_id")
    private AppUser user;

    @ManyToOne
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;

    @Column(name = "submitted_answers")
    private String submittedAnswer;

    @Column(name = "score")
    private int score;

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof QuizAttempt && ((QuizAttempt)obj).getId().equals(this.getId()));
    }
}
