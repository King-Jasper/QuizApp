package com.quizapp.quiz.model.repository;

import com.quizapp.quiz.model.entity.QuizAttempt;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizAttemptRepository extends JpaRepository<QuizAttempt, Long> {
    Page<QuizAttempt> findQuizAttemptsByUserId(Long userId, Pageable pageable);
}
