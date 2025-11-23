package com.charis.api.e_commerce.common.exceptions;

import org.springframework.http.HttpStatus;

public class ResourceNotFoundException extends BaseException {
    public ResourceNotFoundException(String message){
        super(message, HttpStatus.NOT_FOUND.toString(), HttpStatus.NOT_FOUND);
    }
}
