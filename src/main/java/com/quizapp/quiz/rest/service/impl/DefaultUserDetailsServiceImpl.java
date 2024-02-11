package com.quizapp.quiz.rest.service.impl;

import com.quizapp.quiz.model.entity.AppUser;
import com.quizapp.quiz.model.repository.AppUserRepository;
import com.quizapp.quiz.rest.service.UserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DefaultUserDetailsServiceImpl implements UserDetailsService {

    private final AppUserRepository userRepository;

    @Override
    public AppUser resolveUserByEmailAddress(String emailAddress) {
        Optional<AppUser> foundUser;
        if (!Objects.isNull(emailAddress)) {
            foundUser = userRepository.findAppUserByEmail(emailAddress);
            if (foundUser.isEmpty()) {
                throw new UsernameNotFoundException("user not found");
            } else {
                return foundUser.get();
            }
        }
        throw new UsernameNotFoundException("user not found");
    }

    @Override
    public AppUser resolveUserById(Long userId) {
        Optional<AppUser> foundUser;
        if (!Objects.isNull(userId)) {
            foundUser = userRepository.findById(userId);
            if (foundUser.isEmpty()) {
                throw new UsernameNotFoundException("user not found");
            } else {
                return foundUser.get();
            }
        }
        throw new UsernameNotFoundException("user not found");
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        final Optional<AppUser> optionalUser = userRepository.findAppUserByEmail(email);
        if (optionalUser.isEmpty()) {
            throw new UsernameNotFoundException("user not found");
        }
        var foundUser = optionalUser.get();
        if (!foundUser.isActive() || foundUser.isBlocked()) {
            throw new UsernameNotFoundException("user account is not enabled or blocked");
        }

        return org.springframework.security.core.userdetails.User.withUsername(foundUser.getEmail())
                .password(foundUser.getPassword()).accountExpired(false).accountLocked(false).credentialsExpired(false)
                .disabled(false).authorities("GAME PLAYER").build();
    }
}
