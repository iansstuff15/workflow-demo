package dev.byian.workflow.controller;

import dev.byian.workflow.service.TokenService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


@RestController
public class AuthController {

    private static final Logger LOG = LoggerFactory.getLogger(AuthController.class);
    private final TokenService tokenService;
    public AuthController(TokenService tokenService) {
        this.tokenService = tokenService;
    }
    @PostMapping("/token")
    public ResponseEntity<Map<String, String>> token(Authentication authentication){
        Map<String, String> responseMap = new HashMap<>();
        String token = tokenService.generateToken(authentication);
        responseMap.put("token", token);
        return new ResponseEntity<>(responseMap, HttpStatus.OK);
    }
}
