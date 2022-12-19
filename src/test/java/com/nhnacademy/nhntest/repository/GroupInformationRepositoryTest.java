package com.nhnacademy.nhntest.repository;

import com.nhnacademy.nhntest.domain.DepartmentRequest;
import com.nhnacademy.nhntest.domain.EmployeeRequest;
import com.nhnacademy.nhntest.domain.GroupInformationRequest;
import com.nhnacademy.nhntest.entity.Department;
import com.nhnacademy.nhntest.entity.Employee;
import com.nhnacademy.nhntest.entity.GroupInformation;
import com.nhnacademy.nhntest.exception.GroupInformationNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class GroupInformationRepositoryTest {

    @Autowired
    GroupInformationRepository groupInformationRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    EmployeeRequest employeeRequest;
    DepartmentRequest departmentRequest;

    @BeforeEach
    void setUp() {
        employeeRequest = new EmployeeRequest(999, 20201234L, "이현호");
        Employee employee = Employee.builder()
                .employeeNumber(employeeRequest.getEmployeeNumber())
                .name(employeeRequest.getName())
                .build();

        employeeRepository.save(employee);

        departmentRequest = new DepartmentRequest(999, "L9999", "백엔드10팀");
        Department department = Department.builder()
                .departmentCode(departmentRequest.getDepartmentCode())
                .name(departmentRequest.getName())
                .build();

        departmentRepository.save(department);

        GroupInformationRequest groupInformation = new GroupInformationRequest(
                999, employee.getEmployeeNumber(), department.getDepartmentCode());
        groupInformationRepository.save(new GroupInformation(
                groupInformation.getGroupInformationId(),
                employee,
                department
        ));
    }

    @Test
    void findByDepartment_DepartmentCode() {
        //when
        String departmentCode = "L9999";
        List<GroupInformation> actual = groupInformationRepository.findByDepartment_DepartmentCode(departmentCode);

        //then
        assertThat(actual.size()).isEqualTo(1);
    }
}