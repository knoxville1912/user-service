package com.example.medicine.mapper;

import com.example.medicine.model.Doctor;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DoctorMapper implements RowMapper<Doctor> {
    @Override
    public Doctor mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Doctor(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getDate("date_birth").toLocalDate(),
                rs.getString("speciality"),
                rs.getString("mail"),
                rs.getString("phone"),
                rs.getDate("start_date").toLocalDate(),
                rs.getDate("end_date") == null ? null : rs.getDate("end_date").toLocalDate()
        );
    }
}
