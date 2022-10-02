package com.sarachang.springbootdemo.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import com.sarachang.springbootdemo.dao.StationDao;
import com.sarachang.springbootdemo.dao.StationNurseMiddleDao;
import com.sarachang.springbootdemo.dto.StationRequest;
import com.sarachang.springbootdemo.model.Station;
import com.sarachang.springbootdemo.model.StationNurseMiddle;
import com.sarachang.springbootdemo.rowmapper.StationNurseMiddleRowMapper;
import com.sarachang.springbootdemo.rowmapper.StationRowMapper;

@Component
public class StationDaoImpl implements StationDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    private StationNurseMiddleDao stationNurseMiddleDao;

    @Override
    public List<Station> getStations() {
        String sql = "SELECT station_id, station_name, created_date, updated_date FROM station";

        Map<String, Object> map = new HashMap<>();

        List<Station> stationList = namedParameterJdbcTemplate.query(sql, map, new StationRowMapper());

        return stationList;
    }

    @Override
    public List<Station> getStationByNurseId(Integer nurseId) {
        String sql = "SELECT id, station_id, nurse_id, created_date FROM station__nurse WHERE nurse_id = :nurseId";

        Map<String, Object> map = new HashMap<>();
        map.put("nurseId", nurseId);

        List<StationNurseMiddle> middleList = namedParameterJdbcTemplate.query(sql, map,
                new StationNurseMiddleRowMapper());

        List<Station> stationList = new ArrayList<>();

        for (int i = 0; i < middleList.size(); i++) {
            stationList.add(getStationById(middleList.get(i).getStationId()));
        }

        return stationList;
    }

    @Override
    public Station getStationById(Integer stationId) {
        String sql = "SELECT station_id, station_name, created_date, updated_date FROM station WHERE station_id = :stationId";

        Map<String, Object> map = new HashMap<>();
        map.put("stationId", stationId);

        List<Station> stationList = namedParameterJdbcTemplate.query(sql, map, new StationRowMapper());
        if (stationList.size() > 0) {
            return stationList.get(0);
        } else {
            return null;
        }
    }

    @Override
    public Integer createStation(StationRequest stationRequest) {

        String sql = "INSERT INTO station (station_name, created_date, updated_date) VALUES (:stationName, :createdDate, :updatedDate)";

        Map<String, Object> map = new HashMap<>();
        map.put("stationName", stationRequest.getStationName());

        Date now = new Date();
        map.put("createdDate", now);
        map.put("updatedDate", now);

        KeyHolder keyHolder = new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map), keyHolder);

        Integer stationId = keyHolder.getKey().intValue();

        return stationId;
    }

    @Override
    public void updateStation(Integer stationId, StationRequest stationRequest) {

        String sql = "UPDATE station SET station_name = :stationName, updated_date = :updatedDate WHERE station_id = :stationId";

        Map<String, Object> map = new HashMap<>();
        map.put("stationId", stationId);
        map.put("stationName", stationRequest.getStationName());
        map.put("updatedDate", new Date());

        namedParameterJdbcTemplate.update(sql, map);
    }

    @Override
    public void deleteStationById(Integer stationId) {
        String sql = "DELETE FROM station WHERE station_id = :stationId";

        Map<String, Object> map = new HashMap<>();
        map.put("stationId", stationId);

        stationNurseMiddleDao.deleteMiddleByStationId(stationId);
        namedParameterJdbcTemplate.update(sql, map);
    }

}
