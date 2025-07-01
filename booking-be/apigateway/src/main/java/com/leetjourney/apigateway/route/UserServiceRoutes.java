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
public class UserServiceRoutes {

  @Bean
  public RouterFunction<ServerResponse> userRoutes() {
    return GatewayRouterFunctions.route("user-service")

        .route(RequestPredicates.GET("/api/v1/user/{id}"),
            request -> forwardWithPathVariable(request, "id",
                "http://localhost:8080/api/v1/user/"))

        .route(RequestPredicates.PUT("/api/v1/user/{id}"),
            request -> forwardWithPathVariable(request, "id",
                "http://localhost:8080/api/v1/user/"))

        .route(RequestPredicates.DELETE("/api/v1/user/{id}"),
            request -> forwardWithPathVariable(request, "id",
                "http://localhost:8080/api/v1/user/"))

        .route(RequestPredicates.POST("/api/v1/user"),
            request -> forwardRequest(request,
                "http://localhost:8080/api/v1/user"))

        .route(RequestPredicates.GET("/api/v1/user"),
            request -> forwardRequest(request,
                "http://localhost:8080/api/v1/user"))

        .route(RequestPredicates.POST("/api/v1/user/validate-credentials"),
            request -> forwardRequest(request,
                "http://localhost:8080/api/v1/user/validate-credentials"))

        .build();
  }


  private static ServerResponse forwardWithPathVariable(ServerRequest request,
      String pathVariable,
      String baseUrl) throws Exception {
    String value = request.pathVariable(pathVariable);
    return HandlerFunctions.http(baseUrl + value).handle(request);
  }

  private static ServerResponse forwardRequest(ServerRequest request, String targetUrl) throws Exception {
    return HandlerFunctions.http(targetUrl).handle(request);
  }


  @Bean
  public RouterFunction<ServerResponse> userServiceApiDocs() {
    return GatewayRouterFunctions.route("user-service-api-docs")
        .route(RequestPredicates.path("/docs/userservice/v3/api-docs"),
            HandlerFunctions.http("http://localhost:8080"))
        .filter(setPath("/v3/api-docs"))
        .build();
  }
}
