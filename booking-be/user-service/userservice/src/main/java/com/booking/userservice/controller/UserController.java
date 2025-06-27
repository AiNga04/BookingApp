package com.booking.userservice.controller;

import com.booking.userservice.dto.request.CreateUserRequest;
import com.booking.userservice.dto.request.UpdateUserRequest;
import com.booking.userservice.dto.response.ResponseSuccess;
import com.booking.userservice.dto.response.UserResponse;
import com.booking.userservice.service.UserService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
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
  ResponseSuccess getAllUsers(@RequestParam int page, @RequestParam int size) {

    Pageable pageable = PageRequest.of(page, size);

    List<UserResponse> users = userService.getAllUsers(pageable);

    return new ResponseSuccess(HttpStatus.OK, "Get all users successfully", users);
  }

  @GetMapping("/{id}")
  ResponseSuccess getUser(@PathVariable Long id) {
    return new ResponseSuccess(HttpStatus.OK, "Get user successfully", userService.getUserById(id));
  }

  @PutMapping("/{id}")
  ResponseSuccess updateUser(@PathVariable Long id, @Valid @RequestBody UpdateUserRequest req) {

    userService.updateUser(id, req);
    return new ResponseSuccess(HttpStatus.OK, "Update user successfully");
  }

  @DeleteMapping("/{id}")
  ResponseSuccess deleteUser(
      @PathVariable Long id,
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


}
