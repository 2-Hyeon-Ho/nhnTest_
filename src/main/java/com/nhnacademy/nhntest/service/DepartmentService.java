package com.nhnacademy.nhntest.service;

import com.nhnacademy.nhntest.domain.DepartmentDto;
import com.nhnacademy.nhntest.domain.DepartmentRequest;
import com.nhnacademy.nhntest.entity.Department;
import com.nhnacademy.nhntest.exception.DepartmentNotFoundException;
import com.nhnacademy.nhntest.repository.DepartmentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public List<DepartmentDto> getDepartments() {
        List<Department> departments = departmentRepository.findAll();
        return departments.stream()
                .map(o -> new DepartmentDto(o.getDepartmentCode(), o.getName()))
                .collect(Collectors.toList());
    }

    public DepartmentDto getDepartment(String departmentCode) {
        Department department = departmentRepository.findByDepartmentCode(departmentCode)
                .orElseThrow(() -> new DepartmentNotFoundException(departmentCode));

        return DepartmentDto.create(department);
    }

    @Transactional
    public DepartmentDto createDepartment(DepartmentRequest departmentRequest) {
        Department newDepartment = Department.builder()
                .departmentCode(departmentRequest.getDepartmentCode())
                .name(departmentRequest.getName())
                .build();

        return DepartmentDto.create(departmentRepository.save(newDepartment));
    }

    @Transactional
    public DepartmentDto updateDepartment(String departmentCode, DepartmentRequest departmentRequest) {
        Department department = departmentRepository.findByDepartmentCode(departmentCode)
                .orElseThrow(() -> new DepartmentNotFoundException(departmentCode));

        Department modifyDepartment = department.modify(departmentRequest);

        return DepartmentDto.create(departmentRepository.save(modifyDepartment));
    }

    @Transactional
    public void deleteDepartment(String departmentCode) {
        Department department = departmentRepository.findByDepartmentCode(departmentCode)
                .orElseThrow(() -> new DepartmentNotFoundException(departmentCode));

        departmentRepository.delete(department);
    }
}
