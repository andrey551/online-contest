package com.tad.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.jwt.NimbusReactiveJwtDecoder;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverterAdapter;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.ServerAuthenticationSuccessHandler;
import org.springframework.security.web.server.authentication.logout.RedirectServerLogoutSuccessHandler;
import org.springframework.security.web.server.context.NoOpServerSecurityContextRepository;
import org.springframework.web.server.WebFilter;
import reactor.core.publisher.Mono;


import java.net.URI;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfiguration {
    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        // Disable session management (stateless)
        http.securityContextRepository(NoOpServerSecurityContextRepository.getInstance());

        // Configure JWT resource server
        http.oauth2ResourceServer(oauth2 -> oauth2
                .jwt(jwt -> jwt.jwtDecoder(jwtDecoder()))
        );

        // OAuth2 Login Configuration with JWT handling
        http.oauth2Login(oauth2 -> oauth2
                .authenticationSuccessHandler(jwtAuthenticationSuccessHandler())
        );

        // Logout Configuration
        http.logout(logout -> logout
                .logoutSuccessHandler((exchange, authentication) -> {
                    RedirectServerLogoutSuccessHandler handler = new RedirectServerLogoutSuccessHandler();
                    handler.setLogoutSuccessUrl(URI.create("http://localhost:3000"));
                    return handler.onLogoutSuccess(exchange, authentication);
                })
        );

        // Authorization Rules
        http.authorizeExchange(exchanges -> exchanges
                .pathMatchers(
                        "/",
                        "/login",
                        "/oauth2/**",
                        "/logout",
                        "/error"
                ).permitAll()
                .anyExchange().authenticated()
        );

        return http.build();
    }

    @Bean
    public ReactiveJwtDecoder jwtDecoder() {
        return NimbusReactiveJwtDecoder.withJwkSetUri(
                "http://localhost:8080/realms/contest/protocol/openid-connect/certs"
        ).build();
    }

    @Bean
    public ServerAuthenticationSuccessHandler jwtAuthenticationSuccessHandler() {
        return (webFilterExchange, authentication) -> {
            // Extract JWT token from the authentication
            String jwtToken = ((OAuth2AuthenticationToken) authentication).getPrincipal()
                    .getAttribute("id_token");

            // Redirect to frontend with JWT token
            return Mono.fromRunnable(() -> {
                webFilterExchange.getExchange().getResponse().setStatusCode(HttpStatus.FOUND);
                webFilterExchange.getExchange().getResponse().getHeaders().setLocation(
                        URI.create("http://localhost:3000?token=" + jwtToken)
                );
            });
        };
    }

    // Add this filter to convert the token to an Authorization header
    @Bean
    public WebFilter jwtTokenHeaderFilter() {
        return (exchange, chain) -> {
            String token = exchange.getRequest().getQueryParams().getFirst("token");
            if (token != null && !token.isEmpty()) {
                exchange.getRequest().mutate()
                        .header("Authorization", "Bearer " + token)
                        .build();
            }
            return chain.filter(exchange);
        };
    }

    private ReactiveJwtAuthenticationConverterAdapter jwtAuthConverter() {
        JwtGrantedAuthoritiesConverter roles = new JwtGrantedAuthoritiesConverter();
        roles.setAuthoritiesClaimName("roles");
        roles.setAuthorityPrefix(""); // Vì roles bạn đã có dạng "ROLE_ADMIN"
        JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
        converter.setJwtGrantedAuthoritiesConverter(roles);
        return new ReactiveJwtAuthenticationConverterAdapter(converter);
    }

}
