package gal.usc.etse.es.sigrate.controller;

import gal.usc.etse.es.sigrate.model.dto.*;
import gal.usc.etse.es.sigrate.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO register(@RequestBody RegisterRequestDTO data) {
        return authService.register(data);
    }

    @PostMapping("/login")
    public TokenResponseDTO login(@RequestBody LoginRequestDTO credentials) {
        return authService.login(credentials);
    }

    @PostMapping("/refresh")
    public TokenResponseDTO refresh(@RequestBody RefreshRequestDTO request) {
        return authService.refresh(request.refreshToken());
    }

    @PostMapping("/logout")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void logout(@RequestBody RefreshRequestDTO request) {
        authService.logout(request.refreshToken());
    }
}
