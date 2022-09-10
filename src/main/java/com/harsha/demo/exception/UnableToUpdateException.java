package com.harsha.demo.exception;

public class UnableToUpdateException extends RuntimeException {
    public UnableToUpdateException(String msg) {
        super(msg);
    }
}