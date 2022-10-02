package com.sarachang.springbootdemo.dao;

import java.util.List;

import com.sarachang.springbootdemo.dto.NurseRequest;
import com.sarachang.springbootdemo.model.Nurse;

public interface NurseDao {

    List<Nurse> getNurses();

    Nurse getNurseById(Integer nurseId);

    Integer createNurse(NurseRequest nurseRequest);

    void updateNurse(Integer nurseId, NurseRequest nurseRequest);

    void deleteNurseById(Integer nurseId);
}
