package dev.byian.workflow;

import dev.byian.workflow.controller.AuthController;
import dev.byian.workflow.service.TokenService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class AuthControllerJUnitTest {
    @Test
    void testTokenEndpoint() {
        TokenService tokenService = mock(TokenService.class);
        AuthController authController = new AuthController(tokenService);
        Authentication authentication = mock(Authentication.class);

        when(tokenService.generateToken(authentication)).thenReturn("mockedToken");


        ResponseEntity<Map<String, String>> responseEntity = authController.token(authentication);

        verify(tokenService, times(1)).generateToken(authentication);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        Map<String, String> responseBody = responseEntity.getBody();
        assertNotNull(responseBody);
        assertEquals("mockedToken", responseBody.get("token"));
    }
}
