package com.nhnacademy.nhntest.service;

import com.nhnacademy.nhntest.domain.GroupInformationDto;
import com.nhnacademy.nhntest.domain.GroupInformationRequest;
import com.nhnacademy.nhntest.entity.Department;
import com.nhnacademy.nhntest.entity.Employee;
import com.nhnacademy.nhntest.entity.GroupInformation;
import com.nhnacademy.nhntest.exception.DepartmentNotFoundException;
import com.nhnacademy.nhntest.exception.EmployeeNotFoundException;
import com.nhnacademy.nhntest.exception.GroupInformationNotFoundException;
import com.nhnacademy.nhntest.repository.DepartmentRepository;
import com.nhnacademy.nhntest.repository.EmployeeRepository;
import com.nhnacademy.nhntest.repository.GroupInformationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class GroupInformationService {

    private final GroupInformationRepository groupInformationRepository;
    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    public GroupInformationService(GroupInformationRepository groupInformationRepository, EmployeeRepository employeeRepository, DepartmentRepository departmentRepository) {
        this.groupInformationRepository = groupInformationRepository;
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
    }

    public List<GroupInformationDto> getGroupInformation(String departmentCode) {
        return groupInformationRepository.departmentList(departmentCode);
    }

    @Transactional
    public GroupInformationDto createGroupInformation(GroupInformationRequest groupInformationRequest) {
        Employee employee = employeeRepository.findByEmployeeNumber(groupInformationRequest.getEmployeeNumber())
                .orElseThrow(() -> new EmployeeNotFoundException(groupInformationRequest.getEmployeeNumber()));
        Department department = departmentRepository.findByDepartmentCode(groupInformationRequest.getDepartmentCode())
                .orElseThrow(() -> new DepartmentNotFoundException(groupInformationRequest.getDepartmentCode()));

        GroupInformation newGroupInformation = GroupInformation.builder()
                .employee(employee)
                .department(department)
                .build();

        return GroupInformationDto.create(groupInformationRepository.save(newGroupInformation));
    }

//    @Transactional
//    public GroupInformation updateGroupInformation(int groupInformationId, GroupInformationRequest groupInformationRequest) {
//        GroupInformation groupInformation = groupInformationRepository.findById(groupInformationId)
//                .orElseThrow(() -> new GroupInformationNotFoundException(groupInformationId));
//
//        groupInformationRepository.delete(groupInformation);
//
//        return groupInformationRepository.save(createGroupInformation(groupInformationRequest));
//    }

    @Transactional
    public void deleteGroupInformation(int groupInformationId) {
        GroupInformation groupInformation = groupInformationRepository.findById(groupInformationId)
                .orElseThrow(() -> new GroupInformationNotFoundException(groupInformationId));

        groupInformationRepository.delete(groupInformation);
    }
}
