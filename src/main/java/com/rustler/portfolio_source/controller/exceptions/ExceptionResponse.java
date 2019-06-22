package com.rustler.portfolio_source.controller.exceptions;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ExceptionResponse {

    private final String message;
    private final String detailed;

    public ExceptionResponse(
            @JsonProperty(value = "message", required = true) String message,
            @JsonProperty("detailed") String detailed
    ) {
        this.message = message;
        this.detailed = detailed;
    }

    public String getMessage() {
        return message;
    }

    public String getDetailed() {
        return detailed;
    }
}
