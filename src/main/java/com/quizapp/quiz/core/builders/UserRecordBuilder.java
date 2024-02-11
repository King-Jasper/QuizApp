package com.quizapp.quiz.core.builders;

import com.quizapp.quiz.model.payload.user.SignUpRequest;
import com.quizapp.quiz.model.entity.AppUser;
import com.quizapp.quiz.model.payload.user.UserDTO;

public class UserRecordBuilder {

    public static AppUser mapSignupRequestToUser(SignUpRequest signUpRequest) {
        AppUser user = new AppUser();
        user.setFirstName(signUpRequest.getFirstName());
        user.setLastName(signUpRequest.getLastName());
        user.setPhoneNumber(signUpRequest.getPhoneNumber());
        user.setEmail(signUpRequest.getEmailAddress());
        user.setBlocked(false);
        user.setActive(true);
        return user;
    }

    public static UserDTO mapAppUserToUserDTO(AppUser user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setFirstName(userDTO.getFirstName());
        userDTO.setEmailAddress(userDTO.getEmailAddress());
        return userDTO;
    }
}
