package com.sarachang.springbootdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sarachang.springbootdemo.model.Station;
import com.sarachang.springbootdemo.service.StationService;

@RestController
public class StationController {

    @Autowired
    private StationService stationService;

    @GetMapping("/stations/{stationId}")
    public ResponseEntity<Station> getStation(@PathVariable Integer stationId) {
        Station station = stationService.getStationById(stationId);

        if (station != null) {
            return ResponseEntity.status(HttpStatus.OK).body(station);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
