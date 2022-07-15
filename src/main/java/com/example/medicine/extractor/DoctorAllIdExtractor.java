package com.example.medicine.extractor;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DoctorAllIdExtractor implements ResultSetExtractor<List<Long>> {
    @Override
    public List<Long> extractData(ResultSet rs) throws SQLException, DataAccessException {
        List<Long> data = new ArrayList<>();
        while (rs.next()) {
            data.add(rs.getLong("id"));
        }
        return data;
    }
}
