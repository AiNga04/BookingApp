package com.booking.userservice.mapper;

import com.booking.userservice.dto.request.CreateUserRequest;
import com.booking.userservice.dto.request.UpdateUserRequest;
import com.booking.userservice.dto.response.UserResponse;
import com.booking.userservice.enums.Gender;
import com.booking.userservice.model.User;
import java.time.LocalDate;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

  public User toUser(CreateUserRequest req) {
    return User.builder()
        .username(req.getUsername())
        .password(req.getPassword())
        .fullName(req.getFirstName() + " " + req.getLastName())
        .email(req.getEmail())
        .phoneNumber(req.getPhoneNumber())
        .dateOfBirth(LocalDate.parse(req.getDateOfBirth()))
        .gender(req.getGender() ? Gender.MALE : Gender.FEMALE)
        .isEmailVerified(false)
        .isDeleted(false)
        .build();
  }

  public void updateUserFromRequest(User user, UpdateUserRequest req) {
    user.setUsername(req.getUsername());
    user.setFullName(req.getFirstName() + " " + req.getLastName());
    user.setEmail(req.getEmail());
    user.setPhoneNumber(req.getPhoneNumber());
    user.setDateOfBirth(LocalDate.parse(req.getDateOfBirth()));
    user.setGender(req.getGender() ? Gender.MALE : Gender.FEMALE);
  }




  public UserResponse toUserReponse(User req) {
    return UserResponse.builder()
        .username(req.getUsername())
        .password(req.getPassword())
        .fullName(req.getFullName())
        .email(req.getEmail())
        .phoneNumber(req.getPhoneNumber())
        .dateOfBirth(req.getDateOfBirth())
        .gender(req.getGender() == Gender.MALE)
        .isEmailVerified(false)
        .isDeleted(false)
        .build();
  }
}
