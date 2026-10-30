package com.fuelsoft.apifuel.auth;
import java.util.Map;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fuelsoft.apifuel.security.TokenService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	private final AuthenticationManager authManager;
    private final TokenService tokenService;
    private final long expirationMinutes;

    public AuthController(AuthenticationManager authManager,
                          TokenService tokenService,
                          @Value("${app.jwt.expiration-minutes}") long expirationMinutes) {
        this.authManager = authManager;
        this.tokenService = tokenService;
        this.expirationMinutes = expirationMinutes;
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest req) {
        Authentication auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(req.username(), req.password()));
        return new LoginResponse(tokenService.generate(auth), "Bearer", expirationMinutes);
    }
    
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<Map<String, String>> credencialesInvalidas() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(Map.of("error", "Usuario o contraseña incorrectos"));
    }
}
