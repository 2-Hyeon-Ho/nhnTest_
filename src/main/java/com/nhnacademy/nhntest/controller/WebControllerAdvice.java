package com.nhnacademy.nhntest.controller;

import com.nhnacademy.nhntest.exception.DepartmentNotFoundException;
import com.nhnacademy.nhntest.exception.EmployeeNotFoundException;
import com.nhnacademy.nhntest.exception.GroupInformationNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@ControllerAdvice
public class WebControllerAdvice {
    @ExceptionHandler({EmployeeNotFoundException.class, DepartmentNotFoundException.class, GroupInformationNotFoundException.class})
    public void handleNotFoundException(Exception ex, HttpServletResponse response) throws IOException {
        log.error("", ex);

        response.sendError(HttpServletResponse.SC_BAD_REQUEST);
    }

    @ExceptionHandler(RuntimeException.class)
    public String handleRuntimeException(Exception ex, Model model) {
        log.error("", ex);

        model.addAttribute("exception", ex);
        return "error";
    }

    @ExceptionHandler(Exception.class)
    public String handleException(Exception ex, Model model) {
        log.error("", ex);

        model.addAttribute("exception", ex);
        return "error";
    }
}
