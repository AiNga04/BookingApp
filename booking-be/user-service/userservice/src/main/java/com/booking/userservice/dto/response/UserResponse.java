package com.booking.userservice.dto.response;

import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponse {

  private Long id;
  private String username;
  private String fullName;
  private String email;
  private String phoneNumber;
  private LocalDate dateOfBirth;
  private boolean gender;
  private boolean isEmailVerified;
  private String avatarUrl;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
  private LocalDateTime deletedAt;
  private boolean isDeleted;

}
