package com.charis.api.e_commerce.identity.usecase;

import com.charis.api.e_commerce.identity.domain.User;
import com.charis.api.e_commerce.identity.dtos.AuthResponse;
import com.charis.api.e_commerce.identity.dtos.SignUpDto;
import com.charis.api.e_commerce.identity.service.UserService;
import com.charis.api.e_commerce.security.TokenService;
import com.charis.api.e_commerce.security.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserRegistrationUseCase {
    private final UserService userService;
    private final TokenService tokenService;
    private final PasswordEncoder passwordEncoder;

    public AuthResponse execute(SignUpDto payload) {
        if (userService.existsByEmail(payload.getEmail())) {
            throw new IllegalArgumentException("User with this email already exists");
        }

        User user =
                User.builder()
                        .email(payload.getEmail())
                        .password_hash(passwordEncoder.encode(payload.getPassword()))
                        .first_name(payload.getFirst_name())
                        .last_name(payload.getLast_name())
                        .phone_number(payload.getPhone_number())
                        .build();

        User newUser = userService.createUser(user);

        Authentication auth = new UsernamePasswordAuthenticationToken(
                UserPrincipal.from(user),
                null,
                UserPrincipal.from(user).getAuthorities()
        );

        String token = tokenService.generateToken(auth);

        AuthResponse authResponse = new AuthResponse();
        authResponse.setAccessToken(token);
        authResponse.setRole(newUser.getRole());

        return authResponse;
    }
}
