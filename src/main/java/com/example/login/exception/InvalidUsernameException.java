package com.example.login.exception;

public class InvalidUsernameException extends Exception{
    private String message;

    public InvalidUsernameException(String message) {
        this.message = message;
    }

}
