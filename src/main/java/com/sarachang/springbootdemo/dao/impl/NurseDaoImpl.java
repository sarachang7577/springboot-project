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

import com.sarachang.springbootdemo.dao.NurseDao;
import com.sarachang.springbootdemo.dao.StationNurseMiddleDao;
import com.sarachang.springbootdemo.dto.NurseRequest;
import com.sarachang.springbootdemo.dto.StationNurseMiddleRequest;
import com.sarachang.springbootdemo.model.Nurse;
import com.sarachang.springbootdemo.model.StationNurseMiddle;
import com.sarachang.springbootdemo.rowmapper.NurseRowMapper;
import com.sarachang.springbootdemo.rowmapper.StationNurseMiddleRowMapper;

@Component
public class NurseDaoImpl implements NurseDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    private StationNurseMiddleDao stationNurseMiddleDao;

    @Override
    public List<Nurse> getNurses() {
        String sql = "SELECT nurse_id, employee_id, nurse_name, created_date, updated_date FROM nurse";

        Map<String, Object> map = new HashMap<>();

        List<Nurse> nurseList = namedParameterJdbcTemplate.query(sql, map, new NurseRowMapper());

        return nurseList;
    }

    @Override
    public Nurse getNurseById(Integer nurseId) {
        String sql = "SELECT nurse_id, employee_id, nurse_name, created_date, updated_date FROM nurse WHERE nurse_id = :nurseId";

        Map<String, Object> map = new HashMap<>();
        map.put("nurseId", nurseId);

        List<Nurse> nurseList = namedParameterJdbcTemplate.query(sql, map, new NurseRowMapper());
        if (nurseList.size() > 0) {
            return nurseList.get(0);
        } else {
            return null;
        }
    }

    @Override
    public Integer createNurse(NurseRequest nurseRequest) {
        String sql = "INSERT INTO nurse (employee_id, nurse_name, created_date, updated_date) VALUES (:employeeId, :nurseName, :createdDate, :updatedDate)";

        Map<String, Object> map = new HashMap<>();
        map.put("nurseName", nurseRequest.getNurseName());
        map.put("employeeId", nurseRequest.getEmployeeId());

        Date now = new Date();
        map.put("createdDate", now);
        map.put("updatedDate", now);

        KeyHolder keyHolder = new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map), keyHolder);

        Integer nurseId = keyHolder.getKey().intValue();

        stationNurseMiddleDao.createMiddle(nurseRequest.getMiddleList(), nurseId);

        return nurseId;
    }

    @Override
    public void updateNurse(Integer nurseId, NurseRequest nurseRequest) {
        String sql = "UPDATE nurse SET nurse_name = :nurseName, updated_date = :updatedDate WHERE nurse_id = :nurseId";

        Map<String, Object> map = new HashMap<>();
        map.put("nurseId", nurseId);
        map.put("employeeId", nurseRequest.getEmployeeId());
        map.put("nurseName", nurseRequest.getNurseName());
        map.put("updatedDate", new Date());

        stationNurseMiddleDao.createMiddle(nurseRequest.getMiddleList(), nurseId);

        namedParameterJdbcTemplate.update(sql, map);
    }

    @Override
    public void deleteNurseById(Integer nurseId) {
        String sql = "DELETE FROM nurse WHERE nurse_id = :nurseId";

        Map<String, Object> map = new HashMap<>();
        map.put("nurseId", nurseId);

        stationNurseMiddleDao.deleteMiddleByNurseId(nurseId);
        namedParameterJdbcTemplate.update(sql, map);
    }

    @Override
    public List<Nurse> getNursesByStationId(Integer stationId) {
        String sql = "SELECT id, station_id, nurse_id FROM station__nurse WHERE station_id = :stationId";

        Map<String, Object> map = new HashMap<>();
        map.put("stationId", stationId);

        List<StationNurseMiddle> middleList = namedParameterJdbcTemplate.query(sql, map,
                new StationNurseMiddleRowMapper());

        // List<Integer> nurseIdList = middleList.stream().map(m ->
        // m.getNurseId()).collect(Collectors.toList());

        List<Nurse> nurseList = new ArrayList<>();

        for (int i = 0; i < middleList.size(); i++) {
            nurseList.add(getNurseById(middleList.get(i).getNurseId()));
        }

        return nurseList;
    }

}
