package com.nhnacademy.nhntest.controller;

import com.nhnacademy.nhntest.domain.EmployeeDto;
import com.nhnacademy.nhntest.domain.EmployeeRequest;
import com.nhnacademy.nhntest.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
    public List<EmployeeDto> getEmployees() {
        return employeeService.getEmployees();
    }

    @GetMapping("/{employeeNumber}")
    public EmployeeDto getEmployee(@PathVariable Long employeeNumber) {
        return employeeService.getEmployee(employeeNumber);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EmployeeDto createEmployee(@Valid @RequestBody EmployeeRequest employeeRequest) {
        return employeeService.createEmployee(employeeRequest);
    }

    @PutMapping("/{employeeNumber}")
    public EmployeeDto updateEmployee(@PathVariable Long employeeNumber,
                                          @Valid @RequestBody EmployeeRequest employeeRequest) {
        return employeeService.updateEmployee(employeeNumber, employeeRequest);
    }

    @DeleteMapping("/{employeeNumber}")
    public void deleteEmployee(@PathVariable Long employeeNumber) {
        employeeService.deleteEmployee(employeeNumber);
    }
}
