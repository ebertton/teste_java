package com.test.api.services.exceptions;

@SuppressWarnings("serial")
public class DatabaseException extends RuntimeException{
    public DatabaseException(String msg) {
        super(msg);
    }
}