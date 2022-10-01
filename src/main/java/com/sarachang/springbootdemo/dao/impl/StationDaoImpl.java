package com.sarachang.springbootdemo.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import com.sarachang.springbootdemo.dao.StationDao;
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

}
