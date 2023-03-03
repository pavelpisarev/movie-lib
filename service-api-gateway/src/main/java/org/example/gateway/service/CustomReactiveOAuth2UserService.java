package org.example.gateway.service;

import org.example.gateway.model.CustomAuthority;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultReactiveOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.LinkedHashSet;
import java.util.Set;

@Service
public class CustomReactiveOAuth2UserService extends DefaultReactiveOAuth2UserService {
    @Override
    public Mono<OAuth2User> loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        return super.loadUser(userRequest).flatMap((oAuth2User) -> {
            String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails()
                .getUserInfoEndpoint().getUserNameAttributeName();
            Set<GrantedAuthority> mappedAuthorities = new LinkedHashSet<>();
            mappedAuthorities.add(new SimpleGrantedAuthority(CustomAuthority.SCOPE_ACTOR_EDIT.name()));
            mappedAuthorities.add(new SimpleGrantedAuthority(CustomAuthority.SCOPE_GENRE_EDIT.name()));
            oAuth2User = new DefaultOAuth2User(mappedAuthorities, oAuth2User.getAttributes(), userNameAttributeName);
            return Mono.just(oAuth2User);
        });
    }
}