package com.booking.authservice.client;

import com.booking.authservice.dto.request.LoginRequest;
import com.booking.authservice.dto.request.RegisterRequest;
import com.booking.authservice.dto.response.UserResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
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

  public UserResponse login(final LoginRequest loginRequest) {
    String url = userServiceUrl + "/validate-credentials";

    try {
      HttpHeaders headers = new HttpHeaders();
      headers.setContentType(MediaType.APPLICATION_JSON);
      HttpEntity<LoginRequest> requestEntity = new HttpEntity<>(loginRequest, headers);

      ResponseEntity<String> response = restTemplate.exchange(
          url,
          HttpMethod.POST,
          requestEntity,
          String.class
      );

      System.out.println("Response Status: " + response.getStatusCode());
      System.out.println("Response Body: " + response.getBody());

      String body = response.getBody();
      if (body == null || body.isEmpty()) {
        throw new RuntimeException("Response body is null or empty");
      }

      try {
        JsonNode jsonNode = objectMapper.readTree(body);
        int statusCode = jsonNode.get("status").asInt();
        System.out.println("Status field in body: " + statusCode);

        if (statusCode == 200) {
          return objectMapper.readValue(body, UserResponse.class);
        } else {
          throw new ResponseStatusException(
              HttpStatus.UNAUTHORIZED,
              "API returned status: " + statusCode
          );
        }
      } catch (JsonProcessingException e) {
        throw new ResponseStatusException(
            HttpStatus.INTERNAL_SERVER_ERROR,
            "Failed to parse JSON response",
            e
        );
      }

    } catch (HttpClientErrorException | HttpServerErrorException ex) {
      throw new ResponseStatusException(
          ex.getStatusCode(),
          "API error: " + ex.getResponseBodyAsString(),
          ex
      );
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
