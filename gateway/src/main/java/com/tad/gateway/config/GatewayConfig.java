package com.tad.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("frontend-route", r -> r.path("/**")
                        .filters(f -> f
                                .tokenRelay()
                                .removeRequestHeader("Cookie") // Remove cookies for stateless operation
                                .addRequestHeader("Authorization", "Bearer {jwt}") // Forward JWT
                        )
                        .uri("http://localhost:3000"))
                .route("api-route", r -> r.path("/**")
                        .filters(f -> f
                                .tokenRelay()
                                .removeRequestHeader("Cookie")
                                .addRequestHeader("Authorization", "Bearer {jwt}")
                        )
                        .uri("http://http://localhost:3000"))
                .build();
    }
}
