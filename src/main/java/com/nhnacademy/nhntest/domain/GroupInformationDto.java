package com.nhnacademy.nhntest.domain;

import com.nhnacademy.nhntest.entity.GroupInformation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
public class GroupInformationDto {
    private Long employeeNumber;
    private String employeeName;
    private String departmentCode;
    private String departmentName;

    public static GroupInformationDto create(GroupInformation groupInformation) {
        return new GroupInformationDto(
                groupInformation.getEmployee().getEmployeeNumber(),
                groupInformation.getEmployee().getName(),
                groupInformation.getDepartment().getDepartmentCode(),
                groupInformation.getDepartment().getName()
        );
    }
}

