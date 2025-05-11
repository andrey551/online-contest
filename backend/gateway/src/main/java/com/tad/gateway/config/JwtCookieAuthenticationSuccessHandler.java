//package com.tad.gateway.config;
//
//import org.springframework.security.web.server.WebFilterExchange;
//import org.springframework.security.web.server.authentication.ServerAuthenticationSuccessHandler;
//import org.springframework.web.server.ServerWebExchange;
//import reactor.core.publisher.Mono;
//import org.springframework.http.ResponseCookie;
//import org.springframework.http.HttpStatus;
//import java.net.URI;
//import java.time.Duration;
//
//import org.springframework.security.core.Authentication;
//import org.springframework.security.oauth2.jwt.Jwt;
//
//public class JwtCookieAuthenticationSuccessHandler implements ServerAuthenticationSuccessHandler {
//
//    private final String frontendUrl;
//
//    public JwtCookieAuthenticationSuccessHandler(String frontendUrl) {
//        this.frontendUrl = frontendUrl;
//    }
//
//    @Override
//    public Mono<Void> onAuthenticationSuccess(WebFilterExchange webFilterExchange, Authentication authentication) {
//        ServerWebExchange exchange = webFilterExchange.getExchange();
//
//        // 1. Set redirect to frontend
//        exchange.getResponse().setStatusCode(HttpStatus.FOUND);
//        exchange.getResponse().getHeaders().setLocation(URI.create(frontendUrl));
//
//        // 2. Add JWT as HttpOnly Secure cookie
//        if (authentication.getPrincipal() instanceof Jwt) {
//            Jwt jwt = (Jwt) authentication.getPrincipal();
//            ResponseCookie cookie = ResponseCookie.from("token", jwt.getTokenValue())
//                    .httpOnly(true)
//                    .secure(true)  // Enable in production
//                    .path("/")
//                    .maxAge(Duration.ofHours(1))
//                    .sameSite("Lax")  // CSRF protection
//                    .build();
//
//            exchange.getResponse().addCookie(cookie);
//
//            // Optionally add user info as non-HttpOnly cookie
//            ResponseCookie userCookie = ResponseCookie.from("user_info", jwt.getClaimAsString("preferred_username"))
//                    .path("/")
//                    .maxAge(Duration.ofHours(1))
//                    .build();
//            exchange.getResponse().addCookie(userCookie);
//        }
//
//        return exchange.getResponse().setComplete();
//    }
//}
