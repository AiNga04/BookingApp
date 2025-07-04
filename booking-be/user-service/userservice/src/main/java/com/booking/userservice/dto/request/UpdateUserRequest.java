package com.booking.userservice.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserRequest {

  private String username;

  private String firstName;

  private String lastName;

  @Email(message = "Invalid email format")
  private String email;

  @Pattern(
      regexp = "^[0-9]{9,15}$",
      message = "Phone number must be 9-15 digits"
  )
  private String phoneNumber;

  @Pattern(
      regexp = "^\\d{4}-\\d{2}-\\d{2}$",
      message = "Date of birth must be in yyyy-MM-dd format"
  )
  private String dateOfBirth;

  private Boolean gender;

}
