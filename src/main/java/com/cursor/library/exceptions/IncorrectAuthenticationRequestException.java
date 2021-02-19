package com.cursor.library.exceptions;

public class IncorrectAuthenticationRequestException extends RuntimeException {
    private static final String ERROR_MESSAGE = "The authentication data format is incorrect. Couldn't parse the request";

    public IncorrectAuthenticationRequestException() {
        super(ERROR_MESSAGE);
    }
}
