package com.charis.api.e_commerce.identity.usecase;

import com.charis.api.e_commerce.identity.domain.User;
import com.charis.api.e_commerce.identity.domain.UserRole;
import com.charis.api.e_commerce.identity.dtos.AuthResponse;
import com.charis.api.e_commerce.identity.dtos.LoginDto;
import com.charis.api.e_commerce.identity.service.UserService;
import com.charis.api.e_commerce.security.TokenService;
import com.charis.api.e_commerce.security.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserLoginUseCase {
    private final UserService userService;
    private final TokenService tokenService;
    private final AuthenticationManager authenticationManager;

    public AuthResponse execute(LoginDto payload) {
        Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                payload.getEmail(),
                payload.getPassword()
        ));

        String token = tokenService.generateToken(auth);
        UserPrincipal userPrincipal = (UserPrincipal) auth.getPrincipal();

        AuthResponse authResponse = new AuthResponse();
        authResponse.setAccessToken(token);
        authResponse.setRole(userPrincipal.getUser().getRole());


        return authResponse;
    }
}

