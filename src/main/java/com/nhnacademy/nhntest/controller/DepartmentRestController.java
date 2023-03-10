package com.nhnacademy.nhntest.controller;

import com.nhnacademy.nhntest.domain.DepartmentDto;
import com.nhnacademy.nhntest.domain.DepartmentRequest;
import com.nhnacademy.nhntest.exception.AcceptHeaderNotValidException;
import com.nhnacademy.nhntest.exception.ValidationFailedException;
import com.nhnacademy.nhntest.service.DepartmentService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/departments")
public class DepartmentRestController {

    private final DepartmentService departmentService;

    public DepartmentRestController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping
    public List<DepartmentDto> getDepartment(HttpServletRequest request) {
        String accept = request.getHeader("Accept");
        if(accept.equals("application/json")) {
            throw new AcceptHeaderNotValidException(accept);
        }

        return departmentService.getDepartments();
    }

    @GetMapping("/{departmentCode}")
    public DepartmentDto getDepartment(@PathVariable String departmentCode,
                                       HttpServletRequest request) {
        String accept = request.getHeader("Accept");
        if(accept.equals("application/json")) {
            throw new AcceptHeaderNotValidException(accept);
        }

        return departmentService.getDepartment(departmentCode);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DepartmentDto createDepartment(@Valid @RequestBody DepartmentRequest departmentRequest,
                                          BindingResult bindingResult,
                                          HttpServletRequest request) {
        String accept = request.getHeader("Accept");
        if(accept.equals("application/json")) {
            throw new AcceptHeaderNotValidException(accept);
        }

        if(bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }
        return departmentService.createDepartment(departmentRequest);
    }

    @PutMapping("/{departmentCode}")
    public DepartmentDto updateDepartment(@PathVariable String departmentCode,
                                          @Valid @RequestBody DepartmentRequest departmentRequest,
                                          BindingResult bindingResult,
                                          HttpServletRequest request) {
        String accept = request.getHeader("Accept");
        if(accept.equals("application/json")) {
            throw new AcceptHeaderNotValidException(accept);
        }

        if(bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }
        return departmentService.updateDepartment(departmentCode, departmentRequest);
    }

    @DeleteMapping("/{departmentCode}")
    public void deleteDepartment(@PathVariable String departmentCode) {
        departmentService.deleteDepartment(departmentCode);
    }
}
