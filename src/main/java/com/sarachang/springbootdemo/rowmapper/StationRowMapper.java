package com.sarachang.springbootdemo.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;

import com.sarachang.springbootdemo.model.Station;

public class StationRowMapper implements RowMapper<Station> {

    @Override
    @Nullable
    public Station mapRow(ResultSet rs, int rowNum) throws SQLException {

        Station station = new Station();
        station.setStationId(rs.getInt("station_id"));
        station.setStationName(rs.getString("station_name"));
        station.setCreatedDate(rs.getTimestamp("created_date"));
        station.setUpdatedDate(rs.getTimestamp("updated_date"));

        return station;
    }

}
