package com.sarachang.springbootdemo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sarachang.springbootdemo.dto.NurseRequest;
import com.sarachang.springbootdemo.model.Nurse;
import com.sarachang.springbootdemo.service.NurseService;

@RestController
public class NurseController {

    @Autowired
    private NurseService nurseService;

    @GetMapping("/nurses")
    public ResponseEntity<List<Nurse>> getNurses() {
        List<Nurse> nurseList = nurseService.getNurses();

        return ResponseEntity.status(HttpStatus.OK).body(nurseList);
    }

    @GetMapping("/nurses/{nurseId}")
    public ResponseEntity<Nurse> getNurse(@PathVariable Integer nurseId) {
        Nurse nurse = nurseService.getNurseById(nurseId);

        if (nurse != null) {
            return ResponseEntity.status(HttpStatus.OK).body(nurse);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/nurses")
    public ResponseEntity<Nurse> createNurse(@RequestBody @Valid NurseRequest nurseRequest) {
        Integer nurseId = nurseService.createNurse(nurseRequest);

        Nurse nurse = nurseService.getNurseById(nurseId);

        return ResponseEntity.status(HttpStatus.CREATED).body(nurse);
    }

    @PutMapping("/nurses/{nurseId}")
    public ResponseEntity<Nurse> updateNurse(@PathVariable Integer nurseId,
            @RequestBody @Valid NurseRequest nurseRequest) {

        // 檢查 nurse 是否存在
        Nurse nurse = nurseService.getNurseById(nurseId);

        if (nurse == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        nurseService.updateNurse(nurseId, nurseRequest);

        Nurse updatedNurse = nurseService.getNurseById(nurseId);

        return ResponseEntity.status(HttpStatus.OK).body(updatedNurse);
    }

    @DeleteMapping("/nurses/{nurseId}")
    public ResponseEntity<Nurse> deleteNurse(@PathVariable Integer nurseId) {

        nurseService.deleteNurseById(nurseId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
