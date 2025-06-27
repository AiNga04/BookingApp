package com.booking.userservice.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequest {

  @NotBlank(message = "Username cannot be empty or null.")
  private String username;

  @NotBlank(message = "Password cannot be empty or null.")
  private String password;
}