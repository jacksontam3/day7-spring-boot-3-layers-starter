package com.oocl.springbootemployee.exception;

public class EmployeeNotActiveException extends RuntimeException {

    public static final String THIS_EMPLOYEE_IS_NOT_LONGER_ACTIVE = "This Employee is not longer active.";

    public EmployeeNotActiveException() {
        super(THIS_EMPLOYEE_IS_NOT_LONGER_ACTIVE);
    }
}
