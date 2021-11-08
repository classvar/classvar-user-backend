package com.classvar.error.exception;

public class UnauthenticatedUserException extends Exception{
    public UnauthenticatedUserException() {
        super();
    }

    public UnauthenticatedUserException(String message) {
        super(message);
    }

    public UnauthenticatedUserException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnauthenticatedUserException(Throwable cause) {
        super(cause);
    }

    protected UnauthenticatedUserException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
