package com.nhnacademy.nhntest.exception;

public class GroupInformationNotFoundException extends RuntimeException {
    public GroupInformationNotFoundException(int groupInformationId) {
        super(groupInformationId + "is not found");
    }
}
