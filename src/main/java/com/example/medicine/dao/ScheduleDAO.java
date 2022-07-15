package com.example.medicine.dao;

import com.example.medicine.mapper.DoctorMapper;
import com.example.medicine.mapper.MedicalHistoryMapper;
import com.example.medicine.mapper.ScheduleMapper;
import com.example.medicine.model.Doctor;
import com.example.medicine.model.Schedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

@Repository
public class ScheduleDAO {
    @Autowired
    private NamedParameterJdbcTemplate template;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private DoctorDAO doctorDAO;

    public Long getLastIdDoctor() {
        return template.queryForObject(
                "SELECT doctor_id FROM schedule ORDER BY duty_date DESC LIMIT 1",
                new MapSqlParameterSource(),
                Long.class
        );
    }

    public LocalDate getLastDate() {
        return template.queryForObject(
                "SELECT duty_date FROM schedule ORDER BY duty_date DESC LIMIT 1",
                new MapSqlParameterSource(),
                LocalDate.class
        );
    }

    public void createSchedule() {
        List<Long> doctorsId = doctorDAO.getAllIdDoctors();
        List<Schedule> scheduleList = new ArrayList<>();
        int i = doctorsId.indexOf(getLastIdDoctor()) + 1;
        LocalDate lastDate = getLastDate();
        for (; scheduleList.size() < 7; i++) {
            if (i == doctorsId.size()) {
                i = 0;
            }
            scheduleList.add(new Schedule(doctorsId.get(i), lastDate.plusDays(scheduleList.size() + 1)));
        }
        createScheduleFromExcel(scheduleList);
    }

    public void createScheduleFromExcel(List<Schedule> scheduleList) {
        SqlParameterSource[] batch = SqlParameterSourceUtils.createBatch(scheduleList.toArray());
        template.batchUpdate("INSERT INTO schedule (doctor_id, duty_date) VALUES (:doctorId,:dutyDate)", batch);
    }

    public List<Schedule> getScheduleFromDate(LocalDate localDate) {
        return template.query(
                "SELECT *\n" +
                        "FROM schedule\n" +
                        "WHERE duty_date >= :dutyDate",
                new MapSqlParameterSource()
                        .addValue("dutyDate", localDate),
                new ScheduleMapper()
        );
    }

    public void updateScheduleById(Long id, Long doctorId) {
        template.update(
                "UPDATE schedule SET doctor_id = :doctorId " +
                        "WHERE id = :id",
                new MapSqlParameterSource()
                        .addValue("doctorId", doctorId)
                        .addValue("id", id)
        );
    }
}
