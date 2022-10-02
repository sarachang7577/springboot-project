package com.sarachang.springbootdemo.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;

import com.sarachang.springbootdemo.model.Nurse;

public class NurseRowMapper implements RowMapper<Nurse> {

    @Override
    @Nullable
    public Nurse mapRow(ResultSet rs, int rowNum) throws SQLException {
        Nurse nurse = new Nurse();
        nurse.setNurseId(rs.getInt("nurse_id"));
        nurse.setEmployeeId(rs.getString("employee_id"));
        nurse.setNurseName(rs.getString("nurse_name"));
        nurse.setCreatedDate(rs.getTimestamp("created_date"));
        nurse.setUpdatedDate(rs.getTimestamp("updated_date"));
        return nurse;
    }

}
