package com.nhnacademy.nhntest.domain;

import com.nhnacademy.nhntest.entity.Department;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@AllArgsConstructor
@Getter
public class DepartmentRequest {

    @Nullable
    private Integer departmentId;

    @NotBlank
    @Size(min = 1)
    @Size(max = 20)
    private String departmentCode;

    @NotBlank
    @Size(min = 1)
    @Size(max = 20)
    private String name;
}
