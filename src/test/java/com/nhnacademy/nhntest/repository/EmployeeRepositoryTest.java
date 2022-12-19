package com.nhnacademy.nhntest.repository;

import com.nhnacademy.nhntest.domain.EmployeeRequest;
import com.nhnacademy.nhntest.entity.Employee;
import com.nhnacademy.nhntest.exception.EmployeeNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class EmployeeRepositoryTest {

    @Autowired
    EmployeeRepository employeeRepository;

    EmployeeRequest employee;

    @BeforeEach
    void setUp() {
        employee = new EmployeeRequest(999, 20201234L, "이현호");

        employeeRepository.save(new Employee(
                employee.getEmployeeId(),
                employee.getEmployeeNumber(),
                employee.getName()));
    }

    @Test
    void findByEmployeeNumber() {
        //when
        Long employeeNumber = 20201234L;
        Employee actual = employeeRepository.findByEmployeeNumber(employeeNumber)
                .orElseThrow(() -> new EmployeeNotFoundException(employeeNumber));

        //then
        assertThat(actual.getName()).isEqualTo("이현호");
    }
}