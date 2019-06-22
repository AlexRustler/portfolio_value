package com.rustler.portfolio_source.controller.exceptions;

import org.springframework.http.HttpStatus;

public class NotFoundException extends HttpException {

    public NotFoundException(String message, String detailed) {
        super(HttpStatus.NOT_FOUND, message, detailed);
    }
}
