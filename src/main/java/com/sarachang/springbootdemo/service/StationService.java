package com.sarachang.springbootdemo.service;

import java.util.List;

import com.sarachang.springbootdemo.dto.StationRequest;
import com.sarachang.springbootdemo.model.Station;

public interface StationService {

    List<Station> getStations();

    Station getStationById(Integer stationId);

    Integer createStation(StationRequest stationRequest);

    void updateStation(Integer stationId, StationRequest stationRequest);

    void deleteStationById(Integer stationId);

    List<Station> getStationByNurseId(Integer nurseId);

}
