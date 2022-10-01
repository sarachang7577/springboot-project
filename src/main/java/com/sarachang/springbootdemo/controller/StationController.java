package com.sarachang.springbootdemo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sarachang.springbootdemo.dto.StationRequest;
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

    @PostMapping("/stations")
    public ResponseEntity<Station> createStation(@RequestBody @Valid StationRequest stationRequest) {
        Integer stationId = stationService.createStation(stationRequest);

        Station station = stationService.getStationById(stationId);

        return ResponseEntity.status(HttpStatus.CREATED).body(station);
    }

}
