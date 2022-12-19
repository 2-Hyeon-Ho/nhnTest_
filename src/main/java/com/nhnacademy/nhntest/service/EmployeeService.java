package com.nhnacademy.nhntest.service;

import com.nhnacademy.nhntest.domain.EmployeeDto;
import com.nhnacademy.nhntest.domain.EmployeeRequest;
import com.nhnacademy.nhntest.entity.Employee;
import com.nhnacademy.nhntest.exception.EmployeeNotFoundException;
import com.nhnacademy.nhntest.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional(readOnly = true)
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<EmployeeDto> getEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream()
                .map(o -> new EmployeeDto(o.getEmployeeNumber(), o.getName()))
                .collect(Collectors.toList());
    }

    public EmployeeDto getEmployee(Long employeeNumber) {
        Employee employee = employeeRepository.findByEmployeeNumber(employeeNumber)
                .orElseThrow(() -> new EmployeeNotFoundException(employeeNumber));

        return EmployeeDto.create(employee);
    }

    @Transactional
    public EmployeeDto createEmployee(EmployeeRequest employeeRequest) {
        Employee newEmployee = Employee.builder()
                .employeeNumber(employeeRequest.getEmployeeNumber())
                .name(employeeRequest.getName())
                .build();

        return EmployeeDto.create(employeeRepository.save(newEmployee));
    }

    @Transactional
    public EmployeeDto updateEmployee(Long employeeNumber, EmployeeRequest employeeRequest) {
        Employee employee = employeeRepository.findByEmployeeNumber(employeeNumber)
                .orElseThrow(() -> new EmployeeNotFoundException(employeeNumber));

        return EmployeeDto.create(employee.modify(employeeRequest));
    }

    @Transactional
    public void deleteEmployee(Long employeeNumber) {
        Employee employee = employeeRepository.findByEmployeeNumber(employeeNumber)
                .orElseThrow(() -> new EmployeeNotFoundException(employeeNumber));

        employeeRepository.delete(employee);
    }
}
