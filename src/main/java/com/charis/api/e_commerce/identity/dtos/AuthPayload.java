package com.charis.api.e_commerce.identity.dtos;

import lombok.Data;

@Data
public class AuthPayload {
    private String email;
    private String password;
}
