package com.sarachang.springbootdemo.service;

import com.sarachang.springbootdemo.dto.StationRequest;
import com.sarachang.springbootdemo.model.Station;

public interface StationService {

    Station getStationById(Integer stationId);

    Integer createStation(StationRequest stationRequest);

    void updateStation(Integer stationId, StationRequest stationRequest);

    void deleteStationById(Integer stationId);

}
