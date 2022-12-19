package com.nhnacademy.nhntest.repository;

import com.nhnacademy.nhntest.domain.GroupInformationDto;
import com.nhnacademy.nhntest.entity.GroupInformation;
import com.nhnacademy.nhntest.entity.QDepartment;
import com.nhnacademy.nhntest.entity.QEmployee;
import com.nhnacademy.nhntest.entity.QGroupInformation;
import com.querydsl.core.types.Projections;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GroupInformationRepositoryImpl extends QuerydslRepositorySupport implements GroupInformationRepositoryCustom {
    public GroupInformationRepositoryImpl() {
        super(GroupInformation.class);
    }


    /*
    select e.employee_number, e.name, d.department_code, d.name
    from group_information as gi
    inner join employee as e on gi.employee_id = e.employee_id
    inner join department as d on gi.department_id = d.department_id
    where d.department_code='L1001';
     */
    @Override
    public List<GroupInformationDto> departmentList(String departmentCode) {
        QEmployee employee = QEmployee.employee;
        QDepartment department = QDepartment.department;
        QGroupInformation groupInformation = QGroupInformation.groupInformation;

        return from(groupInformation)
                .join(groupInformation.employee, employee)
                .join(groupInformation.department, department)
                .where(department.departmentCode.eq(departmentCode))
                .select(Projections.constructor(
                        GroupInformationDto.class,
                        employee.employeeNumber,
                        employee.name,
                        department.departmentCode,
                        department.name
                )).fetch();
    }
}
