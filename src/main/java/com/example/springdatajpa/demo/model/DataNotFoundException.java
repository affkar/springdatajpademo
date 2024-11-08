package com.example.springdatajpa.demo.model;

public class DataNotFoundException extends RuntimeException {
    public DataNotFoundException(final String message) {
        super(message);
    }

}
