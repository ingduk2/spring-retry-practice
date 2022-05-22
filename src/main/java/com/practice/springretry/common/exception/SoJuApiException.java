package com.practice.springretry.common.exception;

public class SoJuApiException extends RuntimeException{
    public SoJuApiException() {
        super();
    }

    public SoJuApiException(String message) {
        super(message);
    }

    public SoJuApiException(String message, Throwable cause) {
        super(message, cause);
    }

    public SoJuApiException(Throwable cause) {
        super(cause);
    }
}
