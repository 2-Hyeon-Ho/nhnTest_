package com.nhnacademy.nhntest.domain;

import com.nhnacademy.nhntest.entity.Employee;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@AllArgsConstructor
public class EmployeeRequest {

    @Nullable
    private Integer employeeId;

    @NotNull
    private Long employeeNumber;

    @NotBlank
    @Size(min = 1)
    @Size(max = 10)
    private String name;
}
