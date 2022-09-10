package com.harsha.demo.exception;

public class UnableToInsertException extends RuntimeException {
    public UnableToInsertException(String msg) {
        super(msg);
    }
}