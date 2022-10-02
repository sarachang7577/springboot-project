package com.sarachang.springbootdemo.dto;

import java.util.List;

import javax.validation.constraints.NotNull;

public class NurseRequest {

    @NotNull
    private String nurseName;

    private String employeeId;

    private List<StationNurseMiddleRequest> middleList;

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

    public List<StationNurseMiddleRequest> getMiddleList() {
        return middleList;
    }

    public void setMiddleList(List<StationNurseMiddleRequest> middleList) {
        this.middleList = middleList;
    }

}
