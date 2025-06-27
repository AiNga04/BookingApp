package com.booking.userservice.dto.request;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateUserRequest {

  private String username;
  private String password;
  private String rePassword;
  private String firstName;
  private String lastName;
  private String email;
  private String phoneNumber;
  private String dateOfBirth;
  private boolean gender;

}
