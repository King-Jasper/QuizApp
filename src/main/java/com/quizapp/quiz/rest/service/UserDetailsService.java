package com.quizapp.quiz.rest.service;

import com.quizapp.quiz.model.entity.AppUser;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserDetailsService extends org.springframework.security.core.userdetails.UserDetailsService {
    AppUser resolveUserByEmailAddress(String emailAddress);
    AppUser resolveUserById(Long userId);
    UserDetails loadUserByUsername(String username);
}
