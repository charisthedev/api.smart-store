package com.charis.api.e_commerce.identity.dtos;

import com.charis.api.e_commerce.identity.domain.UserRole;
import lombok.Data;

@Data
public class AuthResponse {
    private String accessToken;
    private UserRole role;
}
