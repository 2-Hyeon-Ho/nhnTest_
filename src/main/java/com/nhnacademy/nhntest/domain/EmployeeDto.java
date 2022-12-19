package com.nhnacademy.nhntest.domain;

import com.nhnacademy.nhntest.entity.Employee;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
public class EmployeeDto {
    private Long employeeNumber;
    private String name;

    public static EmployeeDto create(Employee employee) {
        return new EmployeeDto(
                employee.getEmployeeNumber(),
                employee.getName()
        );
    }
}
