package com.sarachang.springbootdemo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sarachang.springbootdemo.dao.StationNurseMiddleDao;
import com.sarachang.springbootdemo.dto.StationNurseMiddleRequest;
import com.sarachang.springbootdemo.model.StationNurseMiddle;
import com.sarachang.springbootdemo.service.StationNurseMiddleService;

@Component
public class StationNurseMiddleServiceImpl implements StationNurseMiddleService {

    @Autowired
    private StationNurseMiddleDao stationNurseMiddleDao;

    @Override
    public List<StationNurseMiddle> getMiddles() {
        return stationNurseMiddleDao.getMiddles();
    }

    @Override
    public List<StationNurseMiddle> createMiddle(List<StationNurseMiddleRequest> stationNurseMiddleRequestList,
            Integer nurseId) {
        return stationNurseMiddleDao.createMiddle(stationNurseMiddleRequestList, nurseId);
    }

    @Override
    public void deleteMiddleByStationId(Integer stationId) {
        stationNurseMiddleDao.deleteMiddleByStationId(stationId);
    }

    @Override
    public void deleteMiddleByNurseId(Integer nurseId) {
        stationNurseMiddleDao.deleteMiddleByNurseId(nurseId);
    }

}
