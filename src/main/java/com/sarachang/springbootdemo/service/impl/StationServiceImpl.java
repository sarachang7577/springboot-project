package com.sarachang.springbootdemo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sarachang.springbootdemo.dao.StationDao;
import com.sarachang.springbootdemo.dto.StationRequest;
import com.sarachang.springbootdemo.model.Station;
import com.sarachang.springbootdemo.service.StationService;

@Component
public class StationServiceImpl implements StationService {

    @Autowired
    private StationDao stationDao;

    @Override
    public List<Station> getStations() {
        return stationDao.getStations();
    }

    @Override
    public Station getStationById(Integer stationId) {
        return stationDao.getStationById(stationId);
    }

    @Override
    public Integer createStation(StationRequest stationRequest) {
        return stationDao.createStation(stationRequest);
    }

    @Override
    public void updateStation(Integer stationId, StationRequest stationRequest) {
        stationDao.updateStation(stationId, stationRequest);
    }

    @Override
    public void deleteStationById(Integer stationId) {
        stationDao.deleteStationById(stationId);
    }

}
