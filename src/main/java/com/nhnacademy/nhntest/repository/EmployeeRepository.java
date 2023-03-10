package com.nhnacademy.nhntest.repository;

import com.nhnacademy.nhntest.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    Optional<Employee> findByEmployeeNumber(Long employeeNumber);
}
