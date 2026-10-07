package com.example.backend.exception;


public class TagNotFoundException extends RuntimeException {

    public TagNotFoundException(String message) {
        super(message);
    }
}