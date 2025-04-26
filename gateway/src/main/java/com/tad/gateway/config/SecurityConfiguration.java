//package com.tad.gateway.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpStatus;
//import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
//import org.springframework.security.config.web.server.ServerHttpSecurity;
//import org.springframework.security.oauth2.client.InMemoryReactiveOAuth2AuthorizedClientService;
//import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
//import org.springframework.security.oauth2.client.ReactiveOAuth2AuthorizedClientService;
//import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
//import org.springframework.security.oauth2.client.registration.ReactiveClientRegistrationRepository;
//import org.springframework.security.oauth2.core.oidc.user.OidcUser;
//import org.springframework.security.oauth2.jwt.NimbusReactiveJwtDecoder;
//import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder;
//import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
//import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
//import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverterAdapter;
//import org.springframework.security.web.server.SecurityWebFilterChain;
//import org.springframework.security.web.server.authentication.ServerAuthenticationSuccessHandler;
//import org.springframework.security.web.server.authentication.logout.RedirectServerLogoutSuccessHandler;
//import org.springframework.security.web.server.context.NoOpServerSecurityContextRepository;
//import org.springframework.web.server.ServerWebExchange;
//import org.springframework.web.server.WebFilter;
//import reactor.core.publisher.Mono;
//
//
//import java.net.URI;
//
//@Configuration
//@EnableWebFluxSecurity
//public class SecurityConfiguration {
//    @Bean
//    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http,ReactiveOAuth2AuthorizedClientService authorizedClientService ) {
//        // Disable session management (stateless)
//        http.securityContextRepository(NoOpServerSecurityContextRepository.getInstance());
//
//        // Configure JWT resource server
//        http.oauth2ResourceServer(oauth2 -> oauth2
//                .jwt(jwt -> jwt.jwtDecoder(jwtDecoder()).jwtAuthenticationConverter(jwtAuthConverter()))
//        );
//
//        // OAuth2 Login Configuration with JWT handling
//        http.oauth2Login(oauth2 -> oauth2
//                .authenticationSuccessHandler(jwtAuthenticationSuccessHandler( authorizedClientService))
//        );
//
//        // Logout Configuration
//        http.logout(logout -> logout
//                .logoutSuccessHandler((exchange, authentication) -> {
//                    RedirectServerLogoutSuccessHandler handler = new RedirectServerLogoutSuccessHandler();
//                    handler.setLogoutSuccessUrl(URI.create("http://localhost:3000"));
//                    return handler.onLogoutSuccess(exchange, authentication);
//                })
//        );
//
//        // Authorization Rules
//        http.authorizeExchange(exchanges -> exchanges
//                .pathMatchers(
//                        "/",
//                        "/login",
//                        "/oauth2/**",
//                        "/logout",
//                        "/error"
//                ).permitAll()
//                .anyExchange().authenticated()
//        );
//
//        return http.build();
//    }
//
//    @Bean
//    public ReactiveJwtDecoder jwtDecoder() {
//        return NimbusReactiveJwtDecoder.withJwkSetUri(
//                "http://localhost:8080/realms/contest/protocol/openid-connect/certs"
//        ).build();
//    }
//
//    @Bean
//    public ServerAuthenticationSuccessHandler jwtAuthenticationSuccessHandler(
//            ReactiveOAuth2AuthorizedClientService authorizedClientService) {
//        return (webFilterExchange, authentication) -> {
//            ServerWebExchange exchange = webFilterExchange.getExchange();
//
//            if (authentication instanceof OAuth2AuthenticationToken oauthToken) {
//                String clientRegistrationId = oauthToken.getAuthorizedClientRegistrationId();
//                OAuth2AuthorizedClient authorizedClient =
//                        authorizedClientService.loadAuthorizedClient(clientRegistrationId, oauthToken.getName()).block();
//
//                String idToken = ((OidcUser) oauthToken.getPrincipal()).getIdToken().getTokenValue();
//
//                exchange.getResponse().setStatusCode(HttpStatus.FOUND);
//                exchange.getResponse().getHeaders().setLocation(
//                        URI.create("http://localhost:3000?token=" + idToken)
//                );
//                return exchange.getResponse().setComplete();
//            }
//
//            return Mono.empty();
//        };
//    }
//
//
//    // Add this filter to convert the token to an Authorization header
//    @Bean
//    public WebFilter jwtTokenHeaderFilter() {
//        return (exchange, chain) -> {
//            String token = exchange.getRequest().getQueryParams().getFirst("token");
//            if (token != null && !token.isEmpty()) {
//                exchange.getRequest().mutate()
//                        .header("Authorization", "Bearer " + token)
//                        .build();
//            }
//            return chain.filter(exchange.mutate()
//                    .request(exchange.getRequest().mutate()
//                            .header("Authorization", "Bearer " + token)
//                            .build())
//                    .build());
//        };
//    }
//
//    @Bean
//    public ReactiveOAuth2AuthorizedClientService authorizedClientService(ReactiveClientRegistrationRepository clientRegistrationRepository) {
//        return new InMemoryReactiveOAuth2AuthorizedClientService(clientRegistrationRepository);
//    }
//
//    private ReactiveJwtAuthenticationConverterAdapter jwtAuthConverter() {
//        JwtGrantedAuthoritiesConverter roles = new JwtGrantedAuthoritiesConverter();
//        roles.setAuthoritiesClaimName("roles");
//        roles.setAuthorityPrefix(""); // Vì roles bạn đã có dạng "ROLE_ADMIN"
//        JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
//        converter.setJwtGrantedAuthoritiesConverter(roles);
//        return new ReactiveJwtAuthenticationConverterAdapter(converter);
//    }
//
//}
