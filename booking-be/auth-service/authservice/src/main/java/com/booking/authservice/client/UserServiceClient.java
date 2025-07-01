package com.booking.authservice.client;

import com.booking.authservice.dto.request.LoginRequest;
import com.booking.authservice.dto.request.RegisterRequest;
import com.booking.authservice.dto.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
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

  @Value("${user.service.url}")
  private String userServiceUrl;

  public UserResponse login(final LoginRequest loginRequest) {
    String url = userServiceUrl + "/validate-credentials";

    try {
      ResponseEntity<UserResponse> response = restTemplate.exchange(
          url,
          HttpMethod.POST,
          new HttpEntity<>(loginRequest),
          UserResponse.class
      );

      if (response.getStatusCode().is2xxSuccessful()) {
        return response.getBody();
      } else {
        throw new ResponseStatusException(
            response.getStatusCode(),
            "API returned status: " + response.getStatusCode()
        );
      }

    } catch (HttpClientErrorException | HttpServerErrorException ex) {
      throw new ResponseStatusException(
          ex.getStatusCode(),
          "API error: " + ex.getResponseBodyAsString(),
          ex
      );
    } catch (Exception ex) {
      throw new RuntimeException("Failed to call User Service API", ex);
    }
  }

  public UserResponse register(final RegisterRequest registerRequest) {
    String url = userServiceUrl + "/register";

    try {
      ResponseEntity<UserResponse> response = restTemplate.exchange(
          url,
          HttpMethod.POST,
          new HttpEntity<>(registerRequest),
          UserResponse.class
      );

      if (response.getStatusCode().is2xxSuccessful()) {
        return response.getBody();
      } else {
        throw new ResponseStatusException(
            response.getStatusCode(),
            "API returned status: " + response.getStatusCode()
        );
      }

    } catch (HttpClientErrorException | HttpServerErrorException ex) {
      throw new ResponseStatusException(
          ex.getStatusCode(),
          "API error: " + ex.getResponseBodyAsString(),
          ex
      );
    } catch (Exception ex) {
      throw new RuntimeException("Failed to call User Service API", ex);
    }
  }
}
