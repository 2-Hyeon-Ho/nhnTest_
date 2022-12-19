package com.nhnacademy.nhntest.entity;

import com.nhnacademy.nhntest.domain.EmployeeRequest;
import lombok.*;

import javax.persistence.*;


@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private Integer employeeId;

    @Column(name = "employee_number")
    private Long employeeNumber;

    @Column(name = "name")
    private String name;

    public Employee modify(EmployeeRequest employeeRequest) {
        this.employeeNumber = employeeRequest.getEmployeeNumber();
        this.name = employeeRequest.getName();

        return this;
    }
}
