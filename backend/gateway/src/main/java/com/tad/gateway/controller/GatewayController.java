package com.tad.gateway.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.security.Principal;

@RestController
public class GatewayController {
    @GetMapping("/health")
    public String health() {
        return "OK";
    }

    @GetMapping("/user")
    public Mono<Principal> currentUser(@AuthenticationPrincipal Principal principal) {
        return Mono.just(principal);
    }

    @GetMapping("/whoami")
    public Mono<String> whoAmI(@AuthenticationPrincipal Jwt jwt) {
        return Mono.just("Logged in as: " + jwt.getClaimAsString("preferred_username"));
    }
}
