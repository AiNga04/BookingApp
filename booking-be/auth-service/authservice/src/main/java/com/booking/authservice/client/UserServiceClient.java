package com.booking.authservice.client;

import com.booking.authservice.dto.request.LoginRequest;
import com.booking.authservice.dto.request.RegisterRequest;
import com.booking.authservice.dto.response.UserResponse;
import com.booking.authservice.exception.InvalidCredentialsException;
import com.booking.authservice.exception.UserAlreadyExistsException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class UserServiceClient {

  private final RestTemplate restTemplate;
  private final ObjectMapper objectMapper;

  @Value("${user.service.url}")
  private String userServiceUrl;

  public UserResponse login(LoginRequest loginRequest) {
    String url = userServiceUrl + "/validate-credentials";

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    HttpEntity<LoginRequest> requestEntity = new HttpEntity<>(loginRequest, headers);

    try {
      ResponseEntity<UserResponse> response = restTemplate.exchange(
          url,
          HttpMethod.POST,
          requestEntity,
          UserResponse.class
      );

      if (response.getStatusCode() == HttpStatus.OK) {
        return response.getBody();
      } else {
        throw new ResponseStatusException(
            response.getStatusCode(),
            "API returned non-200 status: " + response.getStatusCodeValue()
        );
      }
    } catch (HttpClientErrorException | HttpServerErrorException ex) {
      throw new ResponseStatusException(
          ex.getStatusCode(),
          "Invalid username or password",
          ex
      );
    } catch (Exception ex) {
      throw new ResponseStatusException(
          HttpStatus.INTERNAL_SERVER_ERROR,
          "Unexpected error during API call",
          ex
      );
    }
  }


  public UserResponse register(RegisterRequest registerRequest) {
    String url = userServiceUrl + "/register";

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    HttpEntity<RegisterRequest> requestEntity = new HttpEntity<>(registerRequest, headers);

    try {
      ResponseEntity<UserResponse> response = restTemplate.exchange(
          url,
          HttpMethod.POST,
          requestEntity,
          UserResponse.class
      );

      if (response.getStatusCode() == HttpStatus.CREATED) {
        UserResponse userResponse = response.getBody();
        if (userResponse == null) {
          throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Response body is null");
        }
        return userResponse;
      } else {
        if (response.getStatusCode() == HttpStatus.CONFLICT) {
          throw new UserAlreadyExistsException("User already exists");
        } else if (response.getStatusCode() == HttpStatus.BAD_REQUEST) {
          throw new InvalidCredentialsException("Invalid registration data");
        } else {
          throw new ResponseStatusException(
              response.getStatusCode(),
              "API returned status: " + response.getStatusCodeValue()
          );
        }
      }
    } catch (HttpClientErrorException | HttpServerErrorException ex) {
      if (ex.getStatusCode() == HttpStatus.CONFLICT) {
        throw new UserAlreadyExistsException("User already exists");
      } else if (ex.getStatusCode() == HttpStatus.BAD_REQUEST) {
        throw new InvalidCredentialsException("Invalid registration data");
      } else {
        throw new ResponseStatusException(
            ex.getStatusCode(),
            "API error: " + ex.getResponseBodyAsString(),
            ex
        );
      }
    } catch (Exception ex) {
      throw new ResponseStatusException(
          HttpStatus.INTERNAL_SERVER_ERROR,
          "Unexpected error during API call",
          ex
      );
    }
  }
}
