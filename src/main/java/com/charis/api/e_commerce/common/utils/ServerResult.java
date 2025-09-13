package com.charis.api.e_commerce.common.utils;

import lombok.Getter;

@Getter
public class ServerResult<T> {
    private final String message;
    private final T data;

    public ServerResult(String message){
        this.message = message;
        this.data = null;
    }

    public ServerResult(String message, T data){
        this.message = message;
        this.data = data;
    }
}
