//package com.tad.gateway.filters;
//
//import com.tad.gateway.model.RoutePermission;
//import com.tad.gateway.permission.PermissionRegistry;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.cloud.gateway.filter.GatewayFilterChain;
//import org.springframework.cloud.gateway.filter.GlobalFilter;
//import org.springframework.http.HttpStatus;
//import org.springframework.security.oauth2.jwt.Jwt;
//import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
//import org.springframework.stereotype.Component;
//import org.springframework.web.server.ServerWebExchange;
//import reactor.core.publisher.Mono;
//
//import java.security.Principal;
//import java.util.List;
//
//@Slf4j
//@Component
//public class RoleBasedAuthorizationFilter implements GlobalFilter {
//
//    private final List<RoutePermission> permissions;
//
//    public RoleBasedAuthorizationFilter(PermissionRegistry registry) {
//        this.permissions = registry.getAllPermissions();
//    }
//
//    @Override
//    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
//        String path = exchange.getRequest().getPath().value();
//        String method = exchange.getRequest().getMethod().name();
//
//        // 🛡️ Lấy thông tin role từ token
//        Principal principal = exchange.getPrincipal().block();
//
//        if (!(principal instanceof JwtAuthenticationToken authentication)) {
//            return unauthorized(exchange);
//        }
//
//        Jwt jwt = authentication.getToken();
//        List<String> roles = jwt.getClaimAsStringList("roles");
//        log.info("roles:");
//        for( String role : roles ) {
//            log.info(role);
//        }
//
//        // 🕵️‍♂️ Kiểm tra có quyền không
//        boolean hasPermission = permissions.stream().anyMatch(p ->
//                p.method().toString().equalsIgnoreCase(method)
//                        && p.pathPattern().equals(path)
//                        && p.roles().stream().anyMatch(roles::contains)
//        );
//
//        if (!hasPermission) {
//            return forbidden(exchange);
//        }
//
//        return chain.filter(exchange);
//    }
//
//    private Mono<Void> unauthorized(ServerWebExchange exchange) {
//        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
//        return exchange.getResponse().setComplete();
//    }
//
//    private Mono<Void> forbidden(ServerWebExchange exchange) {
//        exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
//        return exchange.getResponse().setComplete();
//    }
//}
