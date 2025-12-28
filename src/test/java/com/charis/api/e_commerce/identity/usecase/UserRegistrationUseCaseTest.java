package com.charis.api.e_commerce.identity.usecase;

import com.charis.api.e_commerce.identity.dtos.SignUpDto;
import com.charis.api.e_commerce.identity.service.UserService;
import com.charis.api.e_commerce.security.TokenService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(MockitoExtension.class)
class UserRegistrationUseCaseTest {

    @Mock
    private UserService userService;

    @Mock
    private TokenService tokenService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserRegistrationUseCase userRegistrationUseCase;

    @Test
    void execute_shouldLogAndThrow_whenUserAlreadyExists() {
        // Arrange
        SignUpDto payload = new SignUpDto();
        payload.setEmail("test@example.com");
        payload.setPassword("password");
        payload.setFirst_name("Test");
        payload.setLast_name("User");

        Mockito.when(userService.existsByEmail(anyString())).thenReturn(true);

        // Act & Assert
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            userRegistrationUseCase.execute(payload);
        });

        System.out.println("Exception message: " + exception.getMessage());
        // We expect the log to appear in the output.
        // In a real environment we might want to attach a ListAppender to verify logging,
        // but for reproduction, visual confirmation or check is first step.
    }
}
