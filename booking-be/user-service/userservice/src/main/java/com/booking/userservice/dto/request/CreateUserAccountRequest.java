package com.booking.userservice.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateUserAccountRequest {

  @NotBlank(message = "Username is required")
  @Size(min = 4, max = 50, message = "Username must be between 4 and 50 characters")
  private String username;

  @NotBlank(message = "Email is required")
  @Email(message = "Invalid email format")
  private String email;

  @NotBlank(message = "First name is required")
  @Size(max = 50, message = "First name must be at most 50 characters")
  private String firstName;

  @NotBlank(message = "Last name is required")
  @Size(max = 50, message = "Last name must be at most 50 characters")
  private String lastName;

  @NotBlank(message = "Password is required")
  @Size(min = 6, max = 100, message = "Password must be between 6 and 100 characters")
  private String password;

  @NotBlank(message = "Password confirmation is required")
  @Size(min = 6, max = 100, message = "Password confirmation must be between 6 and 100 characters")
  private String rePassword;
}
