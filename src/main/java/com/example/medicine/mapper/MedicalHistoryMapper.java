package com.example.medicine.mapper;

import com.example.medicine.model.MedicalHistory;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MedicalHistoryMapper implements RowMapper<MedicalHistory> {
    @Override
    public MedicalHistory mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new MedicalHistory(
                rs.getLong("id"),
                rs.getLong("doctor_id"),
                rs.getLong("patient_id"),
                rs.getString("diagnosis"),
                rs.getDate("start_date").toLocalDate(),
                rs.getDate("end_date") == null ? null : rs.getDate("end_date").toLocalDate()
        );
    }
}
