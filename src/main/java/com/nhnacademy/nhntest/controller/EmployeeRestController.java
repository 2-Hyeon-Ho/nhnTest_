package com.nhnacademy.nhntest.controller;

import com.nhnacademy.nhntest.domain.EmployeeDto;
import com.nhnacademy.nhntest.domain.EmployeeRequest;
import com.nhnacademy.nhntest.exception.AcceptHeaderNotValidException;
import com.nhnacademy.nhntest.exception.ValidationFailedException;
import com.nhnacademy.nhntest.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeRestController {

    private final EmployeeService employeeService;

    public EmployeeRestController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<EmployeeDto> getEmployees(HttpServletRequest request) {
        String accept = request.getHeader("Accept");
        if(accept.equals("application/json")) {
            throw new AcceptHeaderNotValidException(accept);
        }

        return employeeService.getEmployees();
    }

    @GetMapping("/{employeeNumber}")
    public EmployeeDto getEmployee(@PathVariable Long employeeNumber,
                                   HttpServletRequest request) {
        String accept = request.getHeader("Accept");
        if(accept.equals("application/json")) {
            throw new AcceptHeaderNotValidException(accept);
        }

        return employeeService.getEmployee(employeeNumber);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EmployeeDto createEmployee(@Valid @RequestBody EmployeeRequest employeeRequest,
                                      BindingResult bindingResult,
                                      HttpServletRequest request) {
        String accept = request.getHeader("Accept");
        if(accept.equals("application/json")) {
            throw new AcceptHeaderNotValidException(accept);
        }

        if(bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }
        return employeeService.createEmployee(employeeRequest);
    }

    @PutMapping("/{employeeNumber}")
    public EmployeeDto updateEmployee(@PathVariable Long employeeNumber,
                                      @Valid @RequestBody EmployeeRequest employeeRequest,
                                      BindingResult bindingResult,
                                      HttpServletRequest request) {
        String accept = request.getHeader("Accept");
        if(accept.equals("application/json")) {
            throw new AcceptHeaderNotValidException(accept);
        }

        if(bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }
        return employeeService.updateEmployee(employeeNumber, employeeRequest);
    }

    @DeleteMapping("/{employeeNumber}")
    public void deleteEmployee(@PathVariable Long employeeNumber) {
        employeeService.deleteEmployee(employeeNumber);
    }
}
