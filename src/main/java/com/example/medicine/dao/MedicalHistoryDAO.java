package com.example.medicine.dao;

import com.example.medicine.mapper.MedicalHistoryMapper;
import com.example.medicine.model.Doctor;
import com.example.medicine.model.MedicalHistory;
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
public class MedicalHistoryDAO {

    @Autowired
    private NamedParameterJdbcTemplate template;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private DoctorDAO doctorDAO;

    public MedicalHistory createMedicalHistory(MedicalHistory medicalHistory) {
        GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        template.update("INSERT INTO medical_histories (doctor_id, patient_id, diagnosis, start_date, end_date) " +
                        "VALUES (:doctor_id,:patient_id,:diagnosis,:startDate,:endDate)",
                new MapSqlParameterSource()
                        .addValue("doctor_id", medicalHistory.getDoctorId())
                        .addValue("patient_id", medicalHistory.getPatientId())
                        .addValue("diagnosis", medicalHistory.getDiagnosis())
                        .addValue("startDate", medicalHistory.getStartDate())
                        .addValue("endDate", medicalHistory.getEndDate()),
                generatedKeyHolder);
        Integer id = (Integer) generatedKeyHolder.getKeys().get("id");
        return getByMedicalHistoryId(Long.valueOf(id));
    }

    public MedicalHistory getByMedicalHistoryId(Long id) {
        return template.queryForObject("SELECT * FROM medical_histories WHERE id= :id",
                new MapSqlParameterSource()
                        .addValue("id", id),
                new MedicalHistoryMapper());
    }

    public Long getExistingPatientsMedicalHistoryId(Patient patient) {
        return template.queryForObject(
                "SELECT mh.id\n" +
                        "    FROM medical_histories mh\n" +
                        "    JOIN patients p on p.id = mh.patient_id\n" +
                        "    WHERE p.number_oms = :numberOMS\n" +
                        "    AND p.name = :name\n" +
                        "    AND p.date_birth = :dateBirth" +
                        "    ORDER BY id DESC\n" +
                        "    LIMIT 1",
                new MapSqlParameterSource()
                        .addValue("name", patient.getName())
                        .addValue("dateBirth", patient.getDateBirth())
                        .addValue("numberOMS", patient.getNumberOMS()),
                Long.class
        );
    }

    public void patientDischarge(Patient patient) {
        template.update(
                "UPDATE medical_histories\n" +
                        "SET end_date = CURRENT_DATE\n" +
                        "WHERE patient_id = :patientId AND end_date IS NULL",
                new MapSqlParameterSource()
                        .addValue("patientId", patient.getId())
        );
    }

    public boolean isPatientsWithoutDoctors(Doctor doctor) {
        try {
            return Optional.ofNullable(template.queryForObject(
                    "SELECT *\n" +
                            "FROM medical_histories\n" +
                            "WHERE medical_histories.doctor_id = :doctorId\n" +
                            "AND medical_histories.end_date IS NULL",
                    new MapSqlParameterSource()
                            .addValue("doctorID", doctor.getId()),
                    new MedicalHistoryMapper()
            )).isPresent();
        } catch (DataAccessException e) {
            return false;
        }
    }

    public List<MedicalHistory> getAllMedicalHistoryByPatientId(Long id) {
        return template.query(
                "SELECT *\n" +
                        "FROM medical_histories\n" +
                        "WHERE patient_id = :id",
                new MapSqlParameterSource()
                        .addValue("id", id),
                new MedicalHistoryMapper()
        );
    }

    public List<MedicalHistory> getAllMedicalHistoryByDoctorId(Long id) {
        return template.query(
                "SELECT *\n" +
                        "FROM medical_histories\n" +
                        "WHERE doctor_id = :id",
                new MapSqlParameterSource()
                        .addValue("id", id),
                new MedicalHistoryMapper()
        );
    }

    public void updateMedicalHistory(MedicalHistory medicalHistory) {
        template.update(
                "UPDATE medical_histories SET doctor_id = :doctorId, " +
                        "patient_id = :patientId, " +
                        "diagnosis = :diagnosis, " +
                        "start_date = :startDate, " +
                        "end_date = :endDate " +
                        "WHERE id = :id",
                new MapSqlParameterSource()
                        .addValue("doctorId", medicalHistory.getDoctorId())
                        .addValue("patientId", medicalHistory.getPatientId())
                        .addValue("diagnosis", medicalHistory.getDiagnosis())
                        .addValue("startDate", medicalHistory.getStartDate())
                        .addValue("endDate", medicalHistory.getEndDate())
                        .addValue("id", medicalHistory.getId())
        );
    }
}
