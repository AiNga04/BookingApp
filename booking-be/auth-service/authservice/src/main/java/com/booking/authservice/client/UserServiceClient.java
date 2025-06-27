  package com.booking.authservice.client;

  import com.booking.authservice.dto.request.LoginRequest;
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
          throw new RuntimeException("API trả về status: " + response.getStatusCode());
        }

      } catch (HttpClientErrorException | HttpServerErrorException ex) {
        String responseBody = ex.getResponseBodyAsString();
        throw new RuntimeException("Lỗi API: " + ex.getStatusCode() + " - " + responseBody, ex);
      } catch (Exception ex) {
        throw new RuntimeException("Lỗi gọi API", ex);
      }
    }

  }
