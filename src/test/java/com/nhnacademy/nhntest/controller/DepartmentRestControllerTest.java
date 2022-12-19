package com.nhnacademy.nhntest.controller;

import com.nhnacademy.nhntest.domain.DepartmentDto;
import com.nhnacademy.nhntest.domain.DepartmentRequest;
import com.nhnacademy.nhntest.service.DepartmentService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(DepartmentRestController.class)
class DepartmentRestControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    DepartmentService departmentService;

    @Test
    @DisplayName("전체 부서 불러오는 테스트")
    void getDepartment() throws Exception {
        given(departmentService.getDepartments())
                .willReturn(
                        List.of(new DepartmentDto("L9999", "백엔드99팀"),
                                new DepartmentDto("L9998", "백엔드98팀"))
                );

        mvc.perform(get("/departments"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name", equalTo("백엔드99팀")));
    }

    @Test
    void testGetDepartment() throws Exception {
        String departmentCode = "L9999";
        given(departmentService.getDepartment(departmentCode))
                .willReturn(new DepartmentDto("L9999", "백엔드99팀"));

        mvc.perform(get("/departments/"+departmentCode))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name", equalTo("백엔드99팀")));
    }

    @Test
    void createDepartment() throws Exception {
        DepartmentRequest departmentRequest = new DepartmentRequest(999, "L9999", "백엔드99팀");

        DepartmentDto department = new DepartmentDto(departmentRequest.getDepartmentCode(), departmentRequest.getName());
        when(departmentService.createDepartment(departmentRequest)).thenReturn(department);

        mvc.perform(post("/departments")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));
    }
}