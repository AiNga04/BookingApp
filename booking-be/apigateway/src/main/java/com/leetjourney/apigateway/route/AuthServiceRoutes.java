package com.leetjourney.apigateway.route;

import static org.springframework.cloud.gateway.server.mvc.filter.FilterFunctions.setPath;

import org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;

@Configuration
public class AuthServiceRoutes {

  @Bean
  public RouterFunction<ServerResponse> authRoutes() {
    return GatewayRouterFunctions.route("user-service")

        .route(RequestPredicates.POST("/api/v1/auth/login"),
            request -> forwardRequest(request,
                "http://localhost:8081/api/v1/auth/login"))
        .route(RequestPredicates.POST("/api/v1/auth/register"),
            request -> forwardRequest(request,
                "http://localhost:8081/api/v1/auth/register"))

        .build();
  }

  private static ServerResponse forwardWithPathVariable(ServerRequest request,
      String pathVariable,
      String baseUrl) throws Exception {
    String value = request.pathVariable(pathVariable);
    return HandlerFunctions.http(baseUrl + value).handle(request);
  }

  private static ServerResponse forwardRequest(ServerRequest request, String targetUrl)
      throws Exception {
    return HandlerFunctions.http(targetUrl).handle(request);
  }


  @Bean
  public RouterFunction<ServerResponse> authServiceApiDocs() {
    return GatewayRouterFunctions.route("user-service-api-docs")
        .route(RequestPredicates.path("/docs/userservice/v3/api-docs"),
            HandlerFunctions.http("http://localhost:8081"))
        .filter(setPath("/v3/api-docs"))
        .build();
  }
}
