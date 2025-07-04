package com.booking.authservice.dto.response;

import com.booking.authservice.enums.RoleType;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;

@Data
public class UserResponse {

  private Long id;
  private String username;
  private String fullName;
  private String email;
  private String phoneNumber;
  private LocalDate dateOfBirth;
  private boolean gender;
  private boolean emailVerified;
  private String avatarUrl;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
  private LocalDateTime deletedAt;
  private boolean deleted;
  private String roleType;
}
