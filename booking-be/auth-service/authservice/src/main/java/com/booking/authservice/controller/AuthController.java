package com.booking.authservice.controller;

import com.booking.authservice.client.UserServiceClient;
import com.booking.authservice.dto.request.LoginRequest;
import com.booking.authservice.dto.request.RegisterRequest;
import com.booking.authservice.dto.response.ResponseSuccess;
import com.booking.authservice.dto.response.UserResponse;
import com.booking.authservice.service.JwtService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {


  @Autowired
  UserServiceClient userServiceClient;
  private final JwtService jwtService;

  @PostMapping("/login")
  ResponseSuccess login(@Valid @RequestBody LoginRequest loginRequest) {

    System.out.println("hehehe");
    UserResponse userResponse = userServiceClient.login(loginRequest);

    String token = jwtService.generateToken(userResponse.getUsername(), userResponse.getRoleType().name());
    return new ResponseSuccess(HttpStatus.OK, "Login successfully", token);

  }

  @PostMapping("/register")
  ResponseSuccess register(@Valid @RequestBody RegisterRequest registerRequest) {

    return new ResponseSuccess(HttpStatus.CREATED, "Register successfully",
        userServiceClient.register(registerRequest));
  }


}
