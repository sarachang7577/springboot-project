package com.sarachang.springbootdemo.dao;

import java.util.List;

import com.sarachang.springbootdemo.dto.StationNurseMiddleRequest;
import com.sarachang.springbootdemo.model.StationNurseMiddle;

public interface StationNurseMiddleDao {
    List<StationNurseMiddle> getMiddles();

    List<StationNurseMiddle> createMiddle(List<StationNurseMiddleRequest> stationNurseMiddleRequestList,
            Integer nurseId);

    void deleteMiddleByStationId(Integer stationId);

    void deleteMiddleByNurseId(Integer nurseId);
}
