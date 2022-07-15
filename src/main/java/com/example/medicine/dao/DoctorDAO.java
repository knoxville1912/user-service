package com.example.medicine.dao;

import com.example.medicine.extractor.DoctorAllIdExtractor;
import com.example.medicine.mapper.DoctorMapper;
import com.example.medicine.model.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import javax.sql.DataSource;
import java.util.*;

@Repository
public class DoctorDAO {
    @Autowired
    private NamedParameterJdbcTemplate template;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DataSource dataSource;

    public Doctor createDoctor(Doctor doctor) {
        GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        template.update("INSERT INTO doctors (name, date_birth, speciality, mail, phone, start_date, end_date) " +
                        "VALUES (:name,:birthDate,:speciality,:mail,:phone,:startDate,:endDate)",
                new MapSqlParameterSource()
                        .addValue("name", doctor.getName())
                        .addValue("birthDate", doctor.getBirthDate())
                        .addValue("speciality", doctor.getSpeciality())
                        .addValue("mail", doctor.getMail())
                        .addValue("phone", doctor.getPhone())
                        .addValue("startDate", doctor.getStartDate())
                        .addValue("endDate", doctor.getEndDate()),
                generatedKeyHolder);
        Integer id = (Integer) generatedKeyHolder.getKeys().get("id");
        return getByDoctorId(Long.valueOf(id));
    }

    public Long getDoctorsIdWithMinimumPatients() {
        return template.queryForObject(
                "SELECT d.id\n" +
                        "FROM doctors d\n" +
                        "         JOIN medical_histories mh ON d.id = mh.doctor_id\n" +
                        "WHERE mh.end_date IS NULL\n" +
                        "GROUP BY d.name, d.id\n" +
                        "ORDER BY count(*)\n" +
                        "LIMIT 1;",
                new MapSqlParameterSource(),
                Long.class
        );
    }

    public Doctor getByDoctorId(Long id) {
        return template.queryForObject("SELECT * FROM doctors WHERE id= :id",
                new MapSqlParameterSource()
                        .addValue("id", id),
                new DoctorMapper());
    }

    public Doctor getActiveDoctorByPatient(Long id) {
        return template.queryForObject("SELECT d.* FROM doctors d\n" +
                        "    JOIN medical_histories mh on d.id = mh.doctor_id\n" +
                        "WHERE mh.patient_id = :id AND mh.end_date IS NULL;",
                new MapSqlParameterSource()
                        .addValue("id", id),
                new DoctorMapper());
    }

    public List<Long> getAllIdDoctors() {
        return template.query(
                "SELECT id FROM doctors WHERE end_date IS NULL ORDER BY id",
                new DoctorAllIdExtractor()
        );
    }

    private List<Long> getAllPatientIdWithLifeData() {
        return restTemplate.exchange(
                "http://localhost:8081/regular/get-all-patient-id-with-life-data",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Long>>() {
                }
        ).getBody();
    }

    public void quitDoctor(Long id) {
        template.update(
                "UPDATE doctors\n" +
                        "SET end_date = CURRENT_DATE\n" +
                        "WHERE id = :id AND end_date IS NULL",
                new MapSqlParameterSource()
                        .addValue("id", id)
        );
    }

}
