package org.example.gateway;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@EnableWebFluxSecurity
public class SecurityConfig {
    public SecurityWebFilterChain oAuth2Config(ServerHttpSecurity http) {
        http
            .authorizeExchange()
            .pathMatchers("/actuator/**").permitAll()
            .pathMatchers("/api").permitAll()

            .pathMatchers(HttpMethod.PUT, "/api/v1/actors/**").authenticated()
            .pathMatchers(HttpMethod.POST, "/api/v1/actors/**").authenticated()
            .pathMatchers(HttpMethod.DELETE, "/api/v1/actors/**").authenticated()

            .pathMatchers(HttpMethod.PUT, "/api/v1/genres/**").authenticated()
            .pathMatchers(HttpMethod.POST, "/api/v1/genres/**").authenticated()
            .pathMatchers(HttpMethod.DELETE, "/api/v1/genres/**").authenticated()

            .pathMatchers(HttpMethod.PUT, "/api/v1/movies/**").authenticated()
            .pathMatchers(HttpMethod.POST, "/api/v1/movies/**").authenticated()
            .pathMatchers(HttpMethod.DELETE, "/api/v1/movies/**").authenticated()

            .and()
            .oauth2Login();
        return http.build();
    }
}
