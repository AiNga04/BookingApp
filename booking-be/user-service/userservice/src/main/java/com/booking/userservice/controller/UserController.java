package com.booking.userservice.controller;

import com.booking.userservice.dto.request.CreateUserAccountRequest;
import com.booking.userservice.dto.request.CreateUserRequest;
import com.booking.userservice.dto.request.LoginRequest;
import com.booking.userservice.dto.request.UpdateUserRequest;
import com.booking.userservice.dto.response.ResponseSuccess;
import com.booking.userservice.dto.response.UserResponse;
import com.booking.userservice.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import java.util.List;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.modelmapper.internal.bytebuddy.implementation.bind.annotation.Default;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Validated
public class UserController {

  UserService userService;

  @PostMapping
  ResponseSuccess createUser(@Valid @RequestBody CreateUserRequest req) {

    userService.createUser(req);

    return new ResponseSuccess(HttpStatus.CREATED, "User created successfully");
  }

  @GetMapping
  ResponseSuccess getAllUsers(
      @RequestParam(defaultValue = "0", required = false) @Min(0) int page,
      @RequestParam(defaultValue = "10", required = false)  @Min(0) int size) {

    Pageable pageable = PageRequest.of(page, size);

    List<UserResponse> users = userService.getAllUsers(pageable);

    return new ResponseSuccess(HttpStatus.OK, "Get all users successfully", users);
  }

  @GetMapping("/{id}")
  ResponseSuccess getUser(@PathVariable("id") Long id) {
    return new ResponseSuccess(HttpStatus.OK, "Get user successfully", userService.getUserById(id));
  }

  @PutMapping("/{id}")
  ResponseSuccess updateUser(@PathVariable("id") Long id, @Valid @RequestBody UpdateUserRequest req) {

    userService.updateUser(id, req);
    return new ResponseSuccess(HttpStatus.OK, "Update user successfully");
  }

  @DeleteMapping("/{id}")
  ResponseSuccess deleteUser(
      @PathVariable("id") Long id,
      @RequestParam(defaultValue = "false") boolean soft
  ) {
    if (soft) {
      userService.softDeleteUser(id);
      return new ResponseSuccess(HttpStatus.OK, "Soft delete user successfully");
    } else {
      userService.deleteUser(id);
      return new ResponseSuccess(HttpStatus.OK, "Hard delete user successfully");
    }
  }

  @PostMapping("/validate-credentials")
  UserResponse validateCredentials(@Valid @RequestBody LoginRequest req) {

    return userService.validateCredentials(req);
  }

  @PostMapping("/register")
  UserResponse createUserAccount(@Valid @RequestBody CreateUserAccountRequest req) {
    UserResponse userResponse = userService.createUserAccount(req);
    return userResponse;
  }


}
