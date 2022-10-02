package com.sarachang.springbootdemo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sarachang.springbootdemo.dao.NurseDao;
import com.sarachang.springbootdemo.dto.NurseRequest;
import com.sarachang.springbootdemo.model.Nurse;
import com.sarachang.springbootdemo.service.NurseService;

@Component
public class NurseServiceImpl implements NurseService {

    @Autowired
    private NurseDao nurseDao;

    @Override
    public List<Nurse> getNurses() {
        return nurseDao.getNurses();
    }

    @Override
    public Nurse getNurseById(Integer nurseId) {
        return nurseDao.getNurseById(nurseId);
    }

    @Override
    public Integer createNurse(NurseRequest nurseRequest) {
        return nurseDao.createNurse(nurseRequest);
    }

    @Override
    public void updateNurse(Integer nurseId, NurseRequest nurseRequest) {
        nurseDao.updateNurse(nurseId, nurseRequest);
    }

    @Override
    public void deleteNurseById(Integer nurseId) {
        nurseDao.deleteNurseById(nurseId);
    }

}
