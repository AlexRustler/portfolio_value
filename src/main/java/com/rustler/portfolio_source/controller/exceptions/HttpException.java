package com.rustler.portfolio_source.controller.exceptions;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.http.HttpStatus;

public abstract class HttpException extends RuntimeException {

    @JsonProperty("http_status")
    private final HttpStatus httpStatus;
    private final String message;
    private final String detailed;

    HttpException(
            @JsonProperty(value = "http_status", required = true) HttpStatus httpStatus,
            @JsonProperty("message") String message,
            @JsonProperty("detailed") String detailed
    ) {
        super(message);
        this.message = message;
        this.httpStatus = httpStatus;
        this.detailed = detailed;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public String getDetailed() {
        return detailed;
    }
}
