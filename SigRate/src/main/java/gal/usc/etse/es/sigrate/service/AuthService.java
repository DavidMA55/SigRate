package gal.usc.etse.es.sigrate.service;

import gal.usc.etse.es.sigrate.exception.DuplicatedUserException;
import gal.usc.etse.es.sigrate.model.RefreshToken;
import gal.usc.etse.es.sigrate.model.User;
import gal.usc.etse.es.sigrate.model.dto.LoginRequestDTO;
import gal.usc.etse.es.sigrate.model.dto.RegisterRequestDTO;
import gal.usc.etse.es.sigrate.model.dto.TokenResponseDTO;
import gal.usc.etse.es.sigrate.model.dto.UserDTO;
import gal.usc.etse.es.sigrate.repository.RefreshTokenRepository;
import gal.usc.etse.es.sigrate.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.token.Token;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final RefreshTokenRepository refreshTokenRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(AuthenticationManager authenticationManager, JwtService jwtService,
                       RefreshTokenRepository refreshTokenRepository, UserRepository userRepository,
                       PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.refreshTokenRepository = refreshTokenRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public UserDTO register(RegisterRequestDTO data){
        if (userRepository.existsByUsername(data.username())) {
            throw new DuplicatedUserException(data.username());
        }
        if (userRepository.existsByEmail(data.email())) {
            throw new DuplicatedUserException(data.email());
        }
        User user = new User();
        user.setUsername(data.username());
        user.setEmail(data.email());
        user.setPasswordHash(passwordEncoder.encode(data.password()));
        user.setRoles(Set.of("USER"));
        user.setActive(true);
        return UserDTO.from(userRepository.save(user));
    }

    @Transactional
    public TokenResponseDTO login(LoginRequestDTO credentials) {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(credentials.username(), credentials.password()));

        List<String> roles = auth.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .map(r -> r.replace("ROLE_", ""))
                .toList();

        return new TokenResponseDTO(
                jwtService.generateToken(auth.getName(), roles),
                generateRefreshToken(auth.getName()));
    }

    private String generateRefreshToken(String username) {
        User user = userRepository.findByUsername(username).orElseThrow();
        RefreshToken rt = new RefreshToken();
        rt.setToken(UUID.randomUUID().toString());
        rt.setUser(user);
        rt.setExpirationDate(Instant.now().plus(7, ChronoUnit.DAYS));
        refreshTokenRepository.save(rt);
        return rt.getToken();
    }

    @Transactional
    public TokenResponseDTO refresh(String receivedToken) {
        RefreshToken rt = refreshTokenRepository.findByTokenAndRevokedFalse(receivedToken)
                .orElseThrow(() -> new BadCredentialsException("Refresh token inválido o revocado"));

        if(rt.getExpirationDate().isBefore(Instant.now())) {
            refreshTokenRepository.delete(rt);
            throw new BadCredentialsException("El refresh token ha caducado. Inicia sesión de nuevo");
        }

        rt.setRevoked(true);
        refreshTokenRepository.save(rt);

        User user = rt.getUser();
        return new TokenResponseDTO(
                jwtService.generateToken(user.getUsername(), List.copyOf(user.getRoles())),
                generateRefreshToken(user.getUsername()));
    }

    @Transactional
    public void logout(String receivedToken) {
        refreshTokenRepository.findByTokenAndRevokedFalse(receivedToken)
                .ifPresent(rt -> {
                    rt.setRevoked(true);
                    refreshTokenRepository.save(rt);
                });
    }
}
