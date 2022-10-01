package com.sarachang.springbootdemo.dao;

import com.sarachang.springbootdemo.dto.StationRequest;
import com.sarachang.springbootdemo.model.Station;

public interface StationDao {

    Station getStationById(Integer stationId);

    Integer createStation(StationRequest stationRequest);

    void updateStation(Integer stationId, StationRequest stationRequest);

}
