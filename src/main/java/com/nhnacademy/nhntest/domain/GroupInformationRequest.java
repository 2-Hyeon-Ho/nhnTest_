package com.nhnacademy.nhntest.domain;

import com.nhnacademy.nhntest.entity.GroupInformation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Getter
public class GroupInformationRequest {

    @Nullable
    private Integer groupInformationId;

    @NotNull
    private Long employeeNumber;

    @NotNull
    private String departmentCode;
}
