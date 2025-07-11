package com.booking.userservice.mapper;

import com.booking.userservice.dto.request.CreateUserAccountRequest;
import com.booking.userservice.dto.request.CreateUserRequest;
import com.booking.userservice.dto.request.UpdateUserRequest;
import com.booking.userservice.dto.response.UserResponse;
import com.booking.userservice.enums.Gender;
import com.booking.userservice.enums.RoleType;
import com.booking.userservice.exception.RoleTypeNotFoundException;
import com.booking.userservice.model.Role;
import com.booking.userservice.model.User;
import com.booking.userservice.repository.RoleRepository;
import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

  @Autowired
  private RoleRepository roleRepository;

  public User toUser(CreateUserRequest req) {

    Role role = roleRepository.findByRoleType(req.getRoleType()).orElseThrow(() -> new RoleTypeNotFoundException("Role " + req.getRoleType() + " not found"));

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
        .role(role)
        .build();
  }

  public User toUser(CreateUserAccountRequest req) {
    Role role = roleRepository.findByRoleType(RoleType.GUEST).orElseThrow(() -> new RoleTypeNotFoundException("Role " + RoleType.GUEST + " not found"));

    return User.builder()
        .username(req.getUsername())
        .email(req.getEmail())
        .password(req.getPassword())
        .fullName(req.getFirstName() + " " + req.getLastName())
        .role(role)
        .build();
  }

  public void updateUserFromRequest(User user, UpdateUserRequest req) {
    user.setFullName(req.getFirstName() + " " + req.getLastName());
    user.setEmail(req.getEmail());
    user.setPhoneNumber(req.getPhoneNumber());
    user.setDateOfBirth(LocalDate.parse(req.getDateOfBirth()));
    user.setGender(req.getGender() ? Gender.MALE : Gender.FEMALE);
  }




  public UserResponse toUserReponse(User req) {
    return UserResponse.builder()
        .id(req.getId())
        .username(req.getUsername())
        .fullName(req.getFullName())
        .email(req.getEmail())
        .phoneNumber(req.getPhoneNumber())
        .dateOfBirth(req.getDateOfBirth())
        .gender(req.getGender() == Gender.MALE)
        .isEmailVerified(false)
        .isDeleted(false)
        .roleType(req.getRole().getRoleType().name())
        .build();
  }


}
