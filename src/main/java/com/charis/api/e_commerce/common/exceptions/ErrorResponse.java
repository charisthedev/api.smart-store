package com.charis.api.e_commerce.common.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Optional;

@Data
@AllArgsConstructor
public class ErrorResponse {
    private String message;
    private int status;
}
