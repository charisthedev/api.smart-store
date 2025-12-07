package com.charis.api.e_commerce.identity.controllers;

import com.charis.api.e_commerce.identity.dtos.AuthResponse;
import com.charis.api.e_commerce.identity.dtos.LoginDto;
import com.charis.api.e_commerce.identity.dtos.SignUpDto;
import com.charis.api.e_commerce.identity.usecase.UserLoginUseCase;
import com.charis.api.e_commerce.identity.usecase.UserRegistrationUseCase;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserRegistrationUseCase userRegistrationUseCase;
    private final UserLoginUseCase userLoginUseCase;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> signup(@Valid @RequestBody SignUpDto signUpDto) {
       return new ResponseEntity<AuthResponse>(userRegistrationUseCase.execute(signUpDto), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginDto loginDto) {
        return new ResponseEntity<AuthResponse>(userLoginUseCase.execute(loginDto),HttpStatus.OK);
    }
}
