package com.oocl.springbootemployee.exception;

public class EmployeeAgeNotValidException extends RuntimeException {

    public static final String AGE_NOT_VALID = "Age not valid";

    public EmployeeAgeNotValidException() {
        super(AGE_NOT_VALID);
    }
}
