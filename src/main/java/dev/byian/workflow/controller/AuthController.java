package dev.byian.workflow.controller;

import dev.byian.workflow.service.TokenService;
import org.springframework.security.core.Authentication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class AuthController {
    private static final Logger LOG = LoggerFactory.getLogger(AuthController.class);
    private final TokenService tokenService;
    public AuthController(TokenService tokenService) {
        this.tokenService = tokenService;
    }
    @PostMapping("/token")
    public String token(Authentication authentication){
        LOG.debug("Token request for {}", authentication.getName());
        String token = tokenService.generateToken(authentication);
        LOG.debug("Token granted for {}", token);
        return token;
    }
}
