package com.oocl.springbootemployee.exception;

public class EmployeeSalaryNotValidException extends RuntimeException {

    public static final String NOT_VALID_SALARY_WITH_AGE = "Not Valid Salary with Age";

    public EmployeeSalaryNotValidException() {
        super(NOT_VALID_SALARY_WITH_AGE);
    }
}
