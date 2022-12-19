package com.nhnacademy.nhntest.entity;

import com.nhnacademy.nhntest.domain.DepartmentRequest;
import lombok.*;

import javax.persistence.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "department")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "department_id")
    private Integer departmentId;

    @Column(name = "department_code")
    private String departmentCode;

    @Column(name = "name")
    private String name;

    public Department modify(DepartmentRequest departmentRequest) {
        this.departmentCode = departmentRequest.getDepartmentCode();
        this.name = departmentRequest.getName();

        return this;
    }
}
