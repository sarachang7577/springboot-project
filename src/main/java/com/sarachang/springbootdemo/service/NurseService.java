package com.sarachang.springbootdemo.service;

import java.util.List;

import com.sarachang.springbootdemo.dto.NurseRequest;
import com.sarachang.springbootdemo.model.Nurse;

public interface NurseService {

    List<Nurse> getNurses();

    Nurse getNurseById(Integer nurseId);

    Integer createNurse(NurseRequest nurseRequest);

    void updateNurse(Integer nurseId, NurseRequest nurseRequest);

    void deleteNurseById(Integer nurseId);

    List<Nurse> getNursesByStationId(Integer stationId);

}
