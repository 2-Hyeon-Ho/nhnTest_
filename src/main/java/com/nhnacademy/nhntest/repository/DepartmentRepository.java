package com.nhnacademy.nhntest.repository;

import com.nhnacademy.nhntest.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {
    Optional<Department> findByDepartmentCode(String departmentCode);
}
