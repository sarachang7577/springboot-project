package com.sarachang.springbootdemo.dao;

import java.util.List;

import com.sarachang.springbootdemo.dto.StationRequest;
import com.sarachang.springbootdemo.model.Station;

public interface StationDao {

    List<Station> getStations();

    Station getStationById(Integer stationId);

    Integer createStation(StationRequest stationRequest);

    void updateStation(Integer stationId, StationRequest stationRequest);

    void deleteStationById(Integer stationId);
}
