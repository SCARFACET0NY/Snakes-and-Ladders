package com.anton.snl.exception;

public class GameNotActiveException extends RuntimeException {
    private String message;

    public GameNotActiveException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
