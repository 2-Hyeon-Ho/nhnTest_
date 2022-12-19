package com.nhnacademy.nhntest.repository;

import com.nhnacademy.nhntest.entity.GroupInformation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GroupInformationRepository extends JpaRepository<GroupInformation, Integer>, GroupInformationRepositoryCustom {
    List<GroupInformation> findByDepartment_DepartmentCode(String departmentCode);
}
