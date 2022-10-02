package com.sarachang.springbootdemo.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;

import com.sarachang.springbootdemo.model.StationNurseMiddle;

public class StationNurseMiddleRowMapper implements RowMapper<StationNurseMiddle> {

    @Override
    @Nullable
    public StationNurseMiddle mapRow(ResultSet rs, int rowNum) throws SQLException {
        StationNurseMiddle middle = new StationNurseMiddle();
        middle.setId(rs.getInt("id"));
        middle.setStationId(rs.getInt("station_id"));
        middle.setNurseId(rs.getInt("nurse_id"));
        middle.setCreatedDate(rs.getTimestamp("created_date"));
        return middle;
    }

}
