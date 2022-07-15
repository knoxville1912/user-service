package com.example.medicine.dao;


import com.example.medicine.mapper.PatientMapper;
import com.example.medicine.model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@Repository
public class PatientDAO {

    @Autowired
    private NamedParameterJdbcTemplate template;

    @Autowired
    private DataSource dataSource;

    public Patient createPatient(Patient patient) {
        GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        template.update("INSERT INTO patients (name, city, date_birth, number_oms) VALUES (:name,:city,:dateBirth,:numberOMS)",
                new MapSqlParameterSource()
                        .addValue("name", patient.getName())
                        .addValue("city", patient.getCity())
                        .addValue("dateBirth", patient.getDateBirth())
                        .addValue("numberOMS", patient.getNumberOMS()),
                generatedKeyHolder);
        Integer id = (Integer) generatedKeyHolder.getKeys().get("id");
        return getByPatientId(Long.valueOf(id));
    }

    public Patient getByPatientId(Long id) {
        return template.queryForObject("SELECT * FROM patients WHERE id= :id",
                new MapSqlParameterSource()
                        .addValue("id", id),
                new PatientMapper());
    }

    public Long getExistingPatientId(Patient patient) {
        return template.queryForObject(
                "SELECT id\n" +
                        "FROM patients\n" +
                        "WHERE patients.number_oms = :numberOMS\n" +
                        "  AND patients.name = :name\n" +
                        "  AND patients.date_birth = :dateBirth",
                new MapSqlParameterSource()
                        .addValue("name", patient.getName())
                        .addValue("dateBirth", patient.getDateBirth())
                        .addValue("numberOMS", patient.getNumberOMS()),
                Long.class
        );
    }

    public List<Patient> getAllPatientByDoctorId(Long id) {
        return template.query(
                "SELECT p.* FROM patients p\n" +
                        "JOIN medical_histories mh on p.id = mh.patient_id\n" +
                        "WHERE mh.doctor_id = :id AND mh.end_date IS NULL",
                new MapSqlParameterSource()
                        .addValue("id", id),
                new PatientMapper()
        );
    }

    public boolean isPatientExist(Patient patient) {
        try {
            return Optional.ofNullable(template.queryForObject(
                    "SELECT *\n" +
                            "FROM patients\n" +
                            "WHERE patients.number_oms = :numberOMS\n" +
                            "  AND patients.name = :name\n" +
                            "  AND patients.date_birth = :dateBirth;",
                    new MapSqlParameterSource()
                            .addValue("name", patient.getName())
                            .addValue("dateBirth", patient.getDateBirth())
                            .addValue("numberOMS", patient.getNumberOMS()),
                    new PatientMapper()
            )).isPresent();
        } catch (DataAccessException e) {
            return false;
        }
    }

    public Optional<Patient> getPatientByParams(Patient patient) {
        return Optional.ofNullable(template.queryForObject(
                "SELECT *\n" +
                        "FROM patients\n" +
                        "WHERE patients.number_oms = :numberOMS\n" +
                        "  AND patients.name = :name\n" +
                        "  AND patients.date_birth = :dateBirth;",
                new MapSqlParameterSource()
                        .addValue("name", patient.getName())
                        .addValue("dateBirth", patient.getDateBirth())
                        .addValue("numberOMS", patient.getNumberOMS()),
                new PatientMapper()
        ));
    }
}
