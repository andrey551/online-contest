package com.tad.gateway.config;

//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpStatus;
//import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
//import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
//import org.springframework.security.config.web.server.ServerHttpSecurity;
//import org.springframework.security.oauth2.client.*;
//import org.springframework.security.oauth2.client.registration.ReactiveClientRegistrationRepository;
//import org.springframework.security.oauth2.core.oidc.user.OidcUser;
//import org.springframework.security.oauth2.jwt.*;
//import org.springframework.security.oauth2.server.resource.authentication.*;
//import org.springframework.security.web.server.SecurityWebFilterChain;
//import org.springframework.security.web.server.authentication.ServerAuthenticationSuccessHandler;
//import org.springframework.web.server.ServerWebExchange;
//import org.springframework.web.server.WebFilter;
//
//import java.net.URI;
//
//import static org.springframework.security.config.Customizer.withDefaults;
//
//@Configuration
//@EnableWebFluxSecurity
//@EnableReactiveMethodSecurity
//public class SecurityConfiguration {
//
//    @Bean
//    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
//        return http
//                .authorizeExchange(exchange -> exchange
//                        .pathMatchers("/login**", "/error**").permitAll()
//                        .pathMatchers("/login/oauth2/code/**").permitAll()
//                        .anyExchange().authenticated()
//                )
//                .oauth2Login(oauth2 -> oauth2
//                        .authenticationSuccessHandler(
//                                new JwtCookieAuthenticationSuccessHandler("http://localhost:3000")
//                        )
//                )
//                .oauth2Client(withDefaults())
//                .oauth2ResourceServer(oauth2 -> oauth2
//                        .jwt(jwt -> jwt.jwtDecoder(jwtDecoder())
//                                .jwtAuthenticationConverter(jwtAuthenticationConverter()))
//                )
//                .logout(logout -> logout
//                        .logoutSuccessHandler((exchange, authentication) -> {
//                            var handler = new org.springframework.security.web.server.authentication.logout.RedirectServerLogoutSuccessHandler();
//                            handler.setLogoutSuccessUrl(URI.create("http://localhost:3000"));
//                            return handler.onLogoutSuccess(exchange, authentication);
//                        })
//
//                )
//                .csrf(ServerHttpSecurity.CsrfSpec::disable)
//                .build();
//    }
//
//    @Bean
//    public ReactiveJwtDecoder jwtDecoder() {
//        return ReactiveJwtDecoders.fromIssuerLocation("http://keycloak:8080/realms/contest");
//    }
//
//    @Bean
//    public ServerAuthenticationSuccessHandler authenticationSuccessHandler() {
//        return (webFilterExchange, authentication) -> {
//            ServerWebExchange exchange = webFilterExchange.getExchange();
//
//            if (authentication.getPrincipal() instanceof OidcUser oidcUser) {
//                String idToken = oidcUser.getIdToken().getTokenValue();
//
//                // Redirect with token
//                exchange.getResponse().setStatusCode(HttpStatus.FOUND);
//                exchange.getResponse().getHeaders().setLocation(
//                        URI.create("http://localhost:3000?token=" + idToken)
//                );
//                return exchange.getResponse().setComplete();
//            }
//            // Fallback for non-OIDC flows
//            exchange.getResponse().setStatusCode(HttpStatus.FOUND);
//            exchange.getResponse().getHeaders().setLocation(URI.create("http://localhost:3000"));
//            return exchange.getResponse().setComplete();
//        };
//    }
//
//    @Bean
//    public ReactiveOAuth2AuthorizedClientService authorizedClientService(
//            ReactiveClientRegistrationRepository clientRegistrationRepository) {
//        return new InMemoryReactiveOAuth2AuthorizedClientService(clientRegistrationRepository);
//    }
//
//    @Bean
//    public WebFilter jwtTokenHeaderFilter() {
//        return (exchange, chain) -> {
//            String token = exchange.getRequest().getQueryParams().getFirst("token");
//
//            if (token != null && !token.isEmpty()) {
//                exchange = exchange.mutate()
//                        .request(exchange.getRequest().mutate()
//                                .header("Authorization", "Bearer " + token)
//                                .build())
//                        .build();
//            }
//
//            return chain.filter(exchange);
//        };
//    }
//
//    private ReactiveJwtAuthenticationConverterAdapter jwtAuthenticationConverter() {
//        var rolesConverter = new JwtGrantedAuthoritiesConverter();
//        rolesConverter.setAuthoritiesClaimName("roles");
//        rolesConverter.setAuthorityPrefix(""); // roles đã đúng như "STUDENT", "ADMIN", v.v.
//
//        var jwtConverter = new JwtAuthenticationConverter();
//        jwtConverter.setJwtGrantedAuthoritiesConverter(rolesConverter);
//
//        return new ReactiveJwtAuthenticationConverterAdapter(jwtConverter);
//    }
//}

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.client.oidc.web.server.logout.OidcClientInitiatedServerLogoutSuccessHandler;
import org.springframework.security.oauth2.client.registration.ReactiveClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.server.DefaultServerOAuth2AuthorizationRequestResolver;
import org.springframework.security.oauth2.client.web.server.ServerOAuth2AuthorizationRequestResolver;
import org.springframework.security.oauth2.core.DelegatingOAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidatorResult;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.ServerAuthenticationSuccessHandler;
import org.springframework.security.web.server.authentication.logout.ServerLogoutSuccessHandler;
import org.springframework.security.web.server.authorization.ServerAccessDeniedHandler;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.*;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfiguration {

    private static final Logger log = LoggerFactory.getLogger(SecurityConfiguration.class);
    private final ReactiveClientRegistrationRepository clientRegistrationRepository;

    public SecurityConfiguration(ReactiveClientRegistrationRepository clientRegistrationRepository) {
        this.clientRegistrationRepository = clientRegistrationRepository;
    }

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        http
                .authorizeExchange(exchanges -> exchanges
                        .pathMatchers(
                                "/health",
                                "/login**",
                                "/error**",
                                "/webjars/**",
                                "/favicon.ico",
                                "/assets/**",
                                "/actuator/health"
                        ).permitAll()
                        .anyExchange().authenticated()
                )
                .oauth2Login(oauth2 -> oauth2
                        .authenticationSuccessHandler(authenticationSuccessHandler())

                )
                .oauth2Client(withDefaults())
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwt -> jwt
                                .jwtDecoder(jwtDecoder())
                        )
                )
//                .logout(logout -> logout
//                        .logoutSuccessHandler(logoutSuccessHandler())
//                )
                .exceptionHandling(exceptionHandling -> exceptionHandling
                        .accessDeniedHandler(accessDeniedHandler())
                )
                .csrf(ServerHttpSecurity.CsrfSpec::disable);

        return http.build();
    }

    @Bean
    public ServerAuthenticationSuccessHandler authenticationSuccessHandler() {
        log.info("Authentication success handler");
        return (webFilterExchange, authentication) -> {
            ServerWebExchange exchange = webFilterExchange.getExchange();

            if (authentication.getPrincipal() instanceof OidcUser oidcUser) {
                log.info("User authenticated: {}", oidcUser);
                String idToken = oidcUser.getIdToken().getTokenValue();
                exchange.getResponse().setStatusCode(HttpStatus.FOUND);
                exchange.getResponse().getHeaders().setLocation(
                        URI.create("http://localhost:3000?token=" + idToken)
                );
            } else {
                log.info("User unauthenticated: {}", authentication.getPrincipal());
                exchange.getResponse().setStatusCode(HttpStatus.FOUND);
                exchange.getResponse().getHeaders().setLocation(
                        URI.create("http://localhost:3000")
                );
            }
            return exchange.getResponse().setComplete();
        };
    }

    @Bean
    public ServerOAuth2AuthorizationRequestResolver authorizationRequestResolver(
            ReactiveClientRegistrationRepository clientRegistrationRepository) {
        return new DefaultServerOAuth2AuthorizationRequestResolver(clientRegistrationRepository) {
            @Override
            public Mono<OAuth2AuthorizationRequest> resolve(ServerWebExchange exchange) {
                return super.resolve(exchange).map(req -> {
                    // Rewrite auth URL for browser
                    String authUrl = req.getAuthorizationUri()
                            .replace("keycloak:8080", "localhost:8086");
                    return OAuth2AuthorizationRequest.from(req)
                            .authorizationUri(authUrl)
                            .build();
                });
            }
        };
    }

    @Bean
    public ServerLogoutSuccessHandler logoutSuccessHandler() {
        OidcClientInitiatedServerLogoutSuccessHandler handler =
                new OidcClientInitiatedServerLogoutSuccessHandler(clientRegistrationRepository);
        handler.setPostLogoutRedirectUri("http://localhost:3000");
        return handler;
    }

    @Bean
    public ServerAccessDeniedHandler accessDeniedHandler() {
        return (exchange, denied) -> {
            exchange.getResponse().setStatusCode(HttpStatus.FOUND);
            exchange.getResponse().getHeaders().setLocation(URI.create("http://localhost:3000/access-denied"));
            return exchange.getResponse().setComplete();
        };
    }

    @Bean
    public ReactiveJwtDecoder jwtDecoder() {
        // Create decoder with the default issuer (used for metadata discovery)
        NimbusReactiveJwtDecoder jwtDecoder = (NimbusReactiveJwtDecoder)
                ReactiveJwtDecoders.fromIssuerLocation("http://keycloak:8080/realms/contest");

        // Define allowed issuers
        List<String> allowedIssuers = Arrays.asList(
                "http://keycloak:8080/realms/contest",
                "http://localhost:8086/realms/contest"
        );

        // Validate issuer and timestamps
        OAuth2TokenValidator<Jwt> validator = new DelegatingOAuth2TokenValidator<>(
                new JwtTimestampValidator(),
                new JwtIssuerValidator(allowedIssuers) // Custom validator (see below)
        );

        jwtDecoder.setJwtValidator(validator);
        return jwtDecoder;
    }

    // Custom validator to check against multiple issuers
    static class JwtIssuerValidator implements OAuth2TokenValidator<Jwt> {
        private final List<String> allowedIssuers;

        public JwtIssuerValidator(List<String> allowedIssuers) {
            this.allowedIssuers = allowedIssuers;
        }

        @Override
        public OAuth2TokenValidatorResult validate(Jwt jwt) {
            String issuer = jwt.getIssuer().toString();
            if (allowedIssuers.contains(issuer)) {
                return OAuth2TokenValidatorResult.success();
            }
            return OAuth2TokenValidatorResult.failure(
                    new OAuth2Error(
                            "invalid_token",
                            "The issuer '" + issuer + "' is not trusted.",
                            "https://tools.ietf.org/html/rfc6750#section-3.1"
                    )
            );
        }
    }

//    @Bean
//    public ServerOAuth2AuthorizationRequestResolver authorizationRequestResolver(
//            ReactiveClientRegistrationRepository clientRegistrationRepository) {
//        return new DefaultServerOAuth2AuthorizationRequestResolver(clientRegistrationRepository);
//    }
}
