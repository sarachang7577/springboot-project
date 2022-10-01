package com.sarachang.springbootdemo.dto;

import javax.validation.constraints.NotNull;

public class StationRequest {

    @NotNull
    private String stationName;

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

}
