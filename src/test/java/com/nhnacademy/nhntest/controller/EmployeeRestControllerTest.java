package com.nhnacademy.nhntest.controller;

import com.nhnacademy.nhntest.domain.EmployeeDto;
import com.nhnacademy.nhntest.domain.EmployeeRequest;
import com.nhnacademy.nhntest.service.EmployeeService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EmployeeRestController.class)
class EmployeeRestControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    EmployeeService employeeService;

    @Test
    @DisplayName("전체 사원 불러오는 테스트")
    void getEmployees() throws Exception {
        given(employeeService.getEmployees())
                .willReturn(
                        List.of(new EmployeeDto(20209999L, "lee"),
                                new EmployeeDto(20209998L, "hyeono"))
                );

        mvc.perform(get("/employees"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name", equalTo("lee")));
    }

    @Test
    @DisplayName("사번에 맞는 회원 불러오는지 테스트")
    void getEmployee() throws Exception {
        long employeeNumber = 20209999L;
        given(employeeService.getEmployee(employeeNumber))
                .willReturn(new EmployeeDto(20209999L, "lee"));

        mvc.perform(get("/employees/"+employeeNumber))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name", equalTo("lee")));
    }

    @Test
    @DisplayName("회원생성 테스트")
    void createEmployee() throws Exception {
        EmployeeRequest request = new EmployeeRequest(999, 20209999L, "lee");
        EmployeeDto employee = new EmployeeDto(request.getEmployeeNumber(), request.getName());

        when(employeeService.createEmployee(request)).thenReturn(employee);

        mvc.perform(post("/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON));
    }
}