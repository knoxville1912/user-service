package com.example.medicine.mapper;

import com.example.medicine.model.Doctor;
import com.example.medicine.model.Patient;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PatientMapper implements RowMapper<Patient> {
    @Override
    public Patient mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Patient(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getString("city"),
                rs.getDate("date_birth").toLocalDate(),
                rs.getString("number_oms")
        );
    }
}
