package com.tad.gateway.filters;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@Component
public class RoleBasedAuthorizationFilter implements GlobalFilter {

    private final Map<String, List<String>> routeRoles = Map.of(
            "/api/v1/submissions", List.of("ROLE_TEACHER", "ROLE_STUDENT"),
            "/api/v1/courses", List.of("ROLE_ADMIN"),
            "/course/list", List.of("ROLE_STUDENT")
    );

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String path = exchange.getRequest().getPath().toString();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (!routeRoles.containsKey(path)) {
            return unauthorized(exchange);
        }

        if (auth == null || !auth.isAuthenticated()) {
            return unauthorized(exchange);
        }

        Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
        List<String> requiredRoles = routeRoles.get(path);

        boolean allowed = authorities.stream()
                .map(GrantedAuthority::getAuthority)
                .anyMatch(requiredRoles::contains);

        return allowed ? chain.filter(exchange) : forbidden(exchange);
    }

    private Mono<Void> unauthorized(ServerWebExchange exchange) {
        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        return exchange.getResponse().setComplete();
    }

    private Mono<Void> forbidden(ServerWebExchange exchange) {
        exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
        return exchange.getResponse().setComplete();
    }
}
