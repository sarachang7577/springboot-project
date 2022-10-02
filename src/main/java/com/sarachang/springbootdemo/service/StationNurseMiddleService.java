package com.sarachang.springbootdemo.service;

import java.util.List;

import com.sarachang.springbootdemo.dto.StationNurseMiddleRequest;
import com.sarachang.springbootdemo.model.StationNurseMiddle;

public interface StationNurseMiddleService {
    List<StationNurseMiddle> getMiddles();

    List<StationNurseMiddle> createMiddle(List<StationNurseMiddleRequest> stationNurseMiddleRequestList,
            Integer nurseId);

    void deleteMiddleByStationId(Integer stationId);

    void deleteMiddleByNurseId(Integer nurseId);
}
