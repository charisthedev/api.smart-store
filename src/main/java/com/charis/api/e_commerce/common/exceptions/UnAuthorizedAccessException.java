package com.charis.api.e_commerce.common.exceptions;

import org.springframework.http.HttpStatus;

public class UnAuthorizedAccessException extends BaseException {
    public UnAuthorizedAccessException(String message) {

        super(message, HttpStatus.UNAUTHORIZED.toString(), HttpStatus.UNAUTHORIZED);
    }
}
