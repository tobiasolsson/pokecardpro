package com.pokecardpro.auth;

import com.pokecardpro.security.JwtService;
import com.pokecardpro.models.Role;
import com.pokecardpro.models.User;
import com.pokecardpro.repository.UserRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(UserRepository repository, PasswordEncoder passwordEncoder, JwtService jwtService,
                                 AuthenticationManager authenticationManager) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public ResponseEntity<String> register(RegisterRequest request) {
        User user = new User(
                request.firstName(),
                request.lastName(),
                request.email(),
                passwordEncoder.encode(request.password()),
                request.phone(),
                request.street(),
                request.streetNr(),
                request.city(),
                request.zipCode(),
                Role.USER,
                request.wishlist()
        );
        repository.save(user);
        //String token = jwtService.generateToken(user);

        //return new AuthenticationResponse(token);
        return ResponseEntity.status(HttpStatus.CREATED).body("Registration successful!");
    }

    public ResponseEntity<AuthenticationRequest> authenticate(AuthenticationRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.email(), request.password())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        User user = repository.findByEmail(request.email()).orElseThrow();

        ResponseCookie responseCookie = jwtService.generateJwtCookie(user);

        return ResponseEntity.ok()
                             .header(HttpHeaders.SET_COOKIE, responseCookie.toString())
                             .body(new AuthenticationRequest(request.email(), request.password()));
    }

    public boolean getHasAccess(String id) {
        String authenticatedUserEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        String suppliedUserEmail = repository.findById(id).get().getEmail();

        return authenticatedUserEmail.equalsIgnoreCase(suppliedUserEmail);
    }
}
