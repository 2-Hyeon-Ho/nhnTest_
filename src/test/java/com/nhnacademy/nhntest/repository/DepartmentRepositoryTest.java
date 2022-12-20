package com.nhnacademy.nhntest.repository;

import com.nhnacademy.nhntest.domain.DepartmentRequest;
import com.nhnacademy.nhntest.entity.Department;
import com.nhnacademy.nhntest.exception.DepartmentNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class DepartmentRepositoryTest {

    @Autowired
    DepartmentRepository departmentRepository;

    DepartmentRequest department;

    @BeforeEach
    void setUp() {
        department = new DepartmentRequest(999, "L9999", "백엔드10팀");
        departmentRepository.save(new Department(
                department.getDepartmentId(),
                department.getDepartmentCode(),
                department.getName()
        ));
    }

    @Test
    void findByDepartmentCode() {
        //when
        String departmentCode = "L9999";
        Department actual = departmentRepository.findByDepartmentCode(departmentCode)
                .orElseThrow(() -> new DepartmentNotFoundException(departmentCode));

        //then
        assertThat(actual.getName()).isEqualTo("백엔드10팀");
    }
}