package com.nhnacademy.nhntest.controller;

import com.nhnacademy.nhntest.exception.DepartmentNotFoundException;
import com.nhnacademy.nhntest.exception.EmployeeNotFoundException;
import com.nhnacademy.nhntest.exception.GroupInformationNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@RestControllerAdvice
public class ControllerAdvice {
    @ExceptionHandler({EmployeeNotFoundException.class, DepartmentNotFoundException.class, GroupInformationNotFoundException.class})
    public void handleNotFoundException(Exception ex, HttpServletResponse response) throws IOException {
        log.error("", ex);

        response.sendError(HttpServletResponse.SC_BAD_REQUEST);
    }
}
