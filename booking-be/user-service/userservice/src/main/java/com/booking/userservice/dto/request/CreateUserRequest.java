package com.booking.userservice.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateUserRequest {

  @NotBlank(message = "Username is required")
  @Size(min = 4, max = 50, message = "Username must be between 4 and 50 characters")
  private String username;

  @NotBlank(message = "Password is required")
  @Size(min = 6, message = "Password must be at least 6 characters")
  private String password;

  @NotBlank(message = "Re-entered password is required")
  private String rePassword;

  @NotBlank(message = "First name is required")
  private String firstName;

  @NotBlank(message = "Last name is required")
  private String lastName;

  @NotBlank(message = "Email is required")
  @Email(message = "Invalid email format")
  private String email;

  @NotBlank(message = "Phone number is required")
  @Pattern(
      regexp = "^[0-9]{9,15}$",
      message = "Phone number must be 9-15 digits"
  )
  private String phoneNumber;

  @NotBlank(message = "Date of birth is required")
  @Pattern(
      regexp = "^\\d{4}-\\d{2}-\\d{2}$",
      message = "Date of birth must be in yyyy-MM-dd format"
  )
  private String dateOfBirth;

  @NotNull(message = "Gender is required")
  private Boolean gender;

}
