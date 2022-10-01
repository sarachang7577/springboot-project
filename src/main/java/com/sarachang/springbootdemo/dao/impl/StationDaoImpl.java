package com.sarachang.springbootdemo.dao.impl;

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
import com.sarachang.springbootdemo.dto.StationRequest;
import com.sarachang.springbootdemo.model.Station;
import com.sarachang.springbootdemo.rowmapper.StationRowMapper;

@Component
public class StationDaoImpl implements StationDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

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

}
