package com.nhnacademy.nhntest.controller;

import com.nhnacademy.nhntest.domain.GroupInformationDto;
import com.nhnacademy.nhntest.domain.GroupInformationRequest;
import com.nhnacademy.nhntest.entity.GroupInformation;
import com.nhnacademy.nhntest.exception.AcceptHeaderNotValidException;
import com.nhnacademy.nhntest.exception.ValidationFailedException;
import com.nhnacademy.nhntest.service.GroupInformationService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/department-members")
public class GroupInformationRestController {

    private final GroupInformationService groupInformationService;

    public GroupInformationRestController(GroupInformationService groupInformationService) {
        this.groupInformationService = groupInformationService;
    }

    @GetMapping("/{departmentCode}")
    public List<GroupInformationDto> getGroupInformation(@PathVariable String departmentCode,
                                                         HttpServletRequest request) {
        String accept = request.getHeader("Accept");
        if(accept.equals("application/json")) {
            throw new AcceptHeaderNotValidException(accept);
        }

        return groupInformationService.getGroupInformation(departmentCode);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GroupInformationDto createGroupInformation(@Valid @RequestBody GroupInformationRequest groupInformationRequest,
                                                      BindingResult bindingResult,
                                                      HttpServletRequest request) {
        String accept = request.getHeader("Accept");
        if(accept.equals("application/json")) {
            throw new AcceptHeaderNotValidException(accept);
        }

        if(bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }
        return groupInformationService.createGroupInformation(groupInformationRequest);
    }

    @DeleteMapping("/{groupInformationId}")
    public void deleteGroupInformation(@PathVariable int groupInformationId) {
        groupInformationService.deleteGroupInformation(groupInformationId);
    }
}
