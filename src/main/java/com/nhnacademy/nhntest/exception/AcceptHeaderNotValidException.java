package com.nhnacademy.nhntest.exception;

public class AcceptHeaderNotValidException extends RuntimeException {
    public AcceptHeaderNotValidException(String accept) {
        super(accept + "is not valid");
    }
}
