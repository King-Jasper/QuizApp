package com.quizapp.quiz.rest.service.impl;

import com.quizapp.quiz.core.builders.UserRecordBuilder;
import com.quizapp.quiz.core.exception.InvalidCredentialsException;
import com.quizapp.quiz.core.exception.InvalidRequestException;
import com.quizapp.quiz.core.exception.UserNotFoundException;
import com.quizapp.quiz.core.security.JwtService;
import com.quizapp.quiz.core.utils.Constants;
import com.quizapp.quiz.core.utils.PasswordUtils;
import com.quizapp.quiz.model.entity.AppUser;
import com.quizapp.quiz.model.payload.base.BaseResponse;
import com.quizapp.quiz.model.payload.user.AuthenticationToken;
import com.quizapp.quiz.model.payload.user.LoginRequest;
import com.quizapp.quiz.model.payload.user.SignUpRequest;
import com.quizapp.quiz.model.repository.AppUserRepository;
import com.quizapp.quiz.rest.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class DefaultUserServiceImpl implements UserService {

    private final AuthenticationManager authenticationManager;

    private final AppUserRepository userRepository;

    private final PasswordUtils passwordUtils;

    private final JwtService xpressJwtService;

    @Override
    public BaseResponse<?> handleUserRegistration(final SignUpRequest signUpRequest) {
        boolean alreadyExists = userExistsByEmailAddress(signUpRequest.getEmailAddress());
        if (alreadyExists) {
            throw new InvalidRequestException("User already exists");
        }
        AppUser user = UserRecordBuilder.mapSignupRequestToUser(signUpRequest);
        user.setPassword(passwordUtils.hashedPassword(signUpRequest.getPassword()));
        userRepository.save(user);
        return BaseResponse.builder().responseMessage("User registered successfully").responseCode(Constants.SUCCESS_CODE)
                .timeStamp(LocalDateTime.now()).build();
    }


    @Override
    public BaseResponse<?> handleUserLogin(final LoginRequest loginRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmailAddress(), loginRequest.getPassword()));
        } catch (Exception e) {
            throw new InvalidCredentialsException("Wrong username or password, try again.");
        }
        AppUser foundUser = userRepository.findAppUserByEmail(loginRequest.getEmailAddress()).get();
        auditLoginActivity(foundUser);
        AuthenticationToken authenticationToken = xpressJwtService.generateToken(foundUser);
        return BaseResponse.builder().responseCode(Constants.SUCCESS_CODE).responseMessage("User login successful")
                .payload(authenticationToken).build();
    }

    private boolean userExistsByEmailAddress(String emailAddress) {
        return userRepository.existsByEmail(emailAddress);
    }

    private void auditLoginActivity(AppUser foundUser) {
        if (ObjectUtils.isEmpty(foundUser.getFirstLoginDate())) {
            foundUser.setFirstLoginDate(LocalDateTime.now());
        } else {
            foundUser.setLastLoginDate(LocalDateTime.now());
        }
        userRepository.save(foundUser);
    }
}
