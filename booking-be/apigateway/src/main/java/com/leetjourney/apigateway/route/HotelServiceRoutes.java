package com.leetjourney.apigateway.route;


import org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

@Configuration
public class HotelServiceRoutes {

  @Bean
  public RouterFunction<ServerResponse> hotelRoutes() {
    return GatewayRouterFunctions.route("hotel-service")

        .route(RequestPredicates.GET("/api/v1/hotels"),
            HandlerFunctions.http("http://localhost:8082"))
        .route(RequestPredicates.GET("/api/v1/hotels/{id}"),
            HandlerFunctions.http("http://localhost:8082"))
        .route(RequestPredicates.POST("/api/v1/hotels"),
            HandlerFunctions.http("http://localhost:8082"))
        .route(RequestPredicates.PUT("/api/v1/hotels/{id}"),
            HandlerFunctions.http("http://localhost:8082"))
        .route(RequestPredicates.DELETE("/api/v1/hotels/{id}"),
            HandlerFunctions.http("http://localhost:8082"))
        .route(RequestPredicates.GET("/api/v1/hotels/{id}/rooms"),
            HandlerFunctions.http("http://localhost:8082"))

        .route(RequestPredicates.GET("/api/v1/rooms"),
            HandlerFunctions.http("http://localhost:8082"))
        .route(RequestPredicates.GET("/api/v1/rooms/{id}"),
            HandlerFunctions.http("http://localhost:8082"))
        .route(RequestPredicates.POST("/api/v1/rooms"),
            HandlerFunctions.http("http://localhost:8082"))
        .route(RequestPredicates.PUT("/api/v1/rooms/{id}"),
            HandlerFunctions.http("http://localhost:8082"))
        .route(RequestPredicates.DELETE("/api/v1/rooms/{id}"),
            HandlerFunctions.http("http://localhost:8082"))
        .build();
  }
}
