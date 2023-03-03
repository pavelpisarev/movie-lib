package org.example.gateway;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.security.Principal;

@RestController
@RequestMapping("/")
public class TestController {
    @GetMapping
    public Mono<String> k(@AuthenticationPrincipal Mono<Principal> principal) {
        return principal.map(p->p.toString());
    }
}
