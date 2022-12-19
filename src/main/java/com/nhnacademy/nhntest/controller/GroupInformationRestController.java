package com.nhnacademy.nhntest.controller;

import com.nhnacademy.nhntest.domain.GroupInformationDto;
import com.nhnacademy.nhntest.domain.GroupInformationRequest;
import com.nhnacademy.nhntest.entity.GroupInformation;
import com.nhnacademy.nhntest.service.GroupInformationService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
    public List<GroupInformationDto> getGroupInformation(@PathVariable String departmentCode) {
        return groupInformationService.getGroupInformation(departmentCode);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GroupInformationDto createGroupInformation(@Valid @RequestBody GroupInformationRequest groupInformationRequest) {
        return groupInformationService.createGroupInformation(groupInformationRequest);
    }

    @DeleteMapping("/{groupInformationId}")
    public void deleteGroupInformation(@PathVariable int groupInformationId) {
        groupInformationService.deleteGroupInformation(groupInformationId);
    }
}
