package com.sarachang.springbootdemo.dto;

import javax.validation.constraints.NotNull;

public class NurseRequest {

    @NotNull
    private String nurseName;

    private String employeeId;

    public String getNurseName() {
        return nurseName;
    }

    public void setNurseName(String nurseName) {
        this.nurseName = nurseName;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

}
