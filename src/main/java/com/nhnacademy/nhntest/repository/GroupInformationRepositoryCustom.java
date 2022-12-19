package com.nhnacademy.nhntest.repository;

import com.nhnacademy.nhntest.domain.GroupInformationDto;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface GroupInformationRepositoryCustom {
    List<GroupInformationDto> departmentList(String departmentCode);
}
