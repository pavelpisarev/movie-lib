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

            .pathMatchers(HttpMethod.PUT, "/api/v1/actors/**").hasAuthority(Authorities.ACTOR_EDIT.name())
            .pathMatchers(HttpMethod.POST, "/api/v1/actors/**").hasAuthority(Authorities.ACTOR_EDIT.name())
            .pathMatchers(HttpMethod.DELETE, "/api/v1/actors/**").hasAuthority(Authorities.ACTOR_EDIT.name())

                .pathMatchers(HttpMethod.PUT, "/api/v1/genres/**").hasAuthority(Authorities.GENRE_EDIT.name())
            .pathMatchers(HttpMethod.POST, "/api/v1/genres/**").hasAuthority(Authorities.GENRE_EDIT.name())
            .pathMatchers(HttpMethod.DELETE, "/api/v1/genres/**").hasAuthority(Authorities.GENRE_EDIT.name())

            .pathMatchers(HttpMethod.PUT, "/api/v1/movies/**").hasAuthority(Authorities.MOVIE_EDIT.name())
            .pathMatchers(HttpMethod.POST, "/api/v1/movies/**").hasAuthority(Authorities.MOVIE_EDIT.name())
            .pathMatchers(HttpMethod.DELETE, "/api/v1/movies/**").hasAuthority(Authorities.MOVIE_EDIT.name())

            .and()
            .oauth2Login();
        return http.build();
    }
}
