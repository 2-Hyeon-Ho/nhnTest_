package com.nhnacademy.nhntest.exception;

public class DepartmentNotFoundException extends RuntimeException {
    public DepartmentNotFoundException(String departmentCode) {
        super(departmentCode + "is not found");
    }
}
