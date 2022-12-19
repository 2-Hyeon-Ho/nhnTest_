package com.nhnacademy.nhntest.exception;

import com.nhnacademy.nhntest.entity.Employee;

public class EmployeeNotFoundException extends RuntimeException {
    public EmployeeNotFoundException(Long employeeNumber) {
        super(employeeNumber + "is not found");
    }
}
