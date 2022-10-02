package com.sarachang.springbootdemo.dao.impl;

import java.util.Collections;
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

import com.sarachang.springbootdemo.dao.StationNurseMiddleDao;
import com.sarachang.springbootdemo.dto.StationNurseMiddleRequest;
import com.sarachang.springbootdemo.model.StationNurseMiddle;
import com.sarachang.springbootdemo.rowmapper.StationNurseMiddleRowMapper;

@Component
public class StationNurseMiddleDaoImpl implements StationNurseMiddleDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public List<StationNurseMiddle> getMiddles() {
        String sql = "SELECT id, station_id, nurse_id, created_date FROM station__nurse";

        Map<String, Object> map = new HashMap<>();

        List<StationNurseMiddle> middleList = namedParameterJdbcTemplate.query(sql, map,
                new StationNurseMiddleRowMapper());

        return middleList;
    }

    @Override
    public List<StationNurseMiddle> createMiddle(List<StationNurseMiddleRequest> stationNurseMiddleRequestList,
            Integer nurseId) {
        if (stationNurseMiddleRequestList.size() < 0) {
            return Collections.emptyList();
        }

        List<StationNurseMiddle> middles = getMiddles();
        String sql = "INSERT INTO station__nurse (station_id, nurse_id, created_date) VALUES (:stationId, :nurseId, :createdDate)";

        for (StationNurseMiddleRequest rq : stationNurseMiddleRequestList) {
            boolean isDuplicated = isDuplicated(middles, rq);
            if (isDuplicated == false) {
                Map<String, Object> map = new HashMap<>();
                map.put("stationId", rq.getStationId());
                map.put("nurseId", nurseId);
                map.put("createdDate", new Date());

                KeyHolder keyHolder = new GeneratedKeyHolder();

                namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map), keyHolder);
            }
        }

        List<StationNurseMiddle> updatedMiddles = getMiddles();
        return updatedMiddles;
    }

    @Override
    public void deleteMiddleByStationId(Integer stationId) {
        String sql = "DELETE FROM station__nurse WHERE station_id = :stationId;";

        Map<String, Object> map = new HashMap<>();
        map.put("stationId", stationId);

        namedParameterJdbcTemplate.update(sql, map);
    }

    @Override
    public void deleteMiddleByNurseId(Integer nurseId) {
        String sql = "DELETE FROM station__nurse WHERE nurse_id = :nurseId;";

        Map<String, Object> map = new HashMap<>();
        map.put("nurseId", nurseId);

        namedParameterJdbcTemplate.update(sql, map);
    }

    private boolean isDuplicated(List<StationNurseMiddle> middles, StationNurseMiddleRequest data) {
        return middles.stream()
                .anyMatch(m -> m.getStationId() == data.getStationId() && m.getNurseId() == data.getNurseId());
    }

}
