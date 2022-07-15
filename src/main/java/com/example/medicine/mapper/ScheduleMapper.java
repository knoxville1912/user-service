package com.example.medicine.mapper;

import com.example.medicine.model.MedicalHistory;
import com.example.medicine.model.Schedule;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ScheduleMapper implements RowMapper<Schedule> {
    @Override
    public Schedule mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Schedule(
                rs.getLong("id"),
                rs.getLong("doctor_id"),
                rs.getDate("duty_date").toLocalDate()
        );
    }
}
