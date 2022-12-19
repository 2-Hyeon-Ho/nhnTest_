package com.nhnacademy.nhntest.domain;

import com.nhnacademy.nhntest.entity.Department;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class DepartmentDto {
    private String departmentCode;
    private String name;

    public static DepartmentDto create(Department department) {
        return new DepartmentDto(
                department.getDepartmentCode(),
                department.getName()
        );
    }
}
