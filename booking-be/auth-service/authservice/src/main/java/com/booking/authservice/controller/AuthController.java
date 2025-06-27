package com.booking.authservice.controller;

import com.booking.authservice.client.UserServiceClient;
import com.booking.authservice.dto.request.LoginRequest;
import com.booking.authservice.dto.response.ResponseSuccess;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthController {


  UserServiceClient userServiceClient;

  @PostMapping("/login")
  ResponseSuccess login(@Valid @RequestBody LoginRequest loginRequest) {

    return new ResponseSuccess(HttpStatus.CREATED, "Login successfully", userServiceClient.login(loginRequest));

  }





}
