package com.example.medicine.service;

import com.example.medicine.dao.DoctorDAO;
import com.example.medicine.dao.MedicalHistoryDAO;
import com.example.medicine.dao.PatientDAO;
import com.example.medicine.model.MedicalHistory;
import com.example.medicine.model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class PatientService {

    @Autowired
    private PatientDAO patientDAO;

    @Autowired
    private DoctorDAO doctorDAO;

    @Autowired
    private MedicalHistoryDAO medicalHistoryDAO;

    public Patient createPatientWithMedicalHistory(Patient patient, String diagnosis) {
        if (patientDAO.isPatientExist(patient)) {
            medicalHistoryDAO.createMedicalHistory(new MedicalHistory(
                    doctorDAO.getDoctorsIdWithMinimumPatients(),
                    patientDAO.getExistingPatientId(patient),
                    diagnosis,
                    LocalDate.now(),
                    null
            ));
            return patientDAO.getByPatientId(patient.getId());

        } else {
            Patient patientCreated = patientDAO.createPatient(patient);
            medicalHistoryDAO.createMedicalHistory(new MedicalHistory(
                    doctorDAO.getDoctorsIdWithMinimumPatients(),
                    patientDAO.getExistingPatientId(patient),
                    diagnosis,
                    LocalDate.now(),
                    null
            ));
            return patientDAO.getByPatientId(patientCreated.getId());
        }
    }

    public List<Patient> getAllPatientByDoctorId(Long id) {
        return patientDAO.getAllPatientByDoctorId(id);
    }

    public void patientDischarge(Patient patient) {
//        Optional<Patient> patientByParams = patientDAO.getPatientByParams(patientName, birthDate, numberOMS);
//        if (patientByParams.isPresent()) {
//            medicalHistoryDAO.patientDischarge(patientByParams.get());
//        }
//        medicalHistoryDAO.patientDischarge(patientByParams.orElseThrow(()-> new RuntimeException("No such patient")));
        patientDAO.getPatientByParams(patient)
                .ifPresent(patient1 -> medicalHistoryDAO.patientDischarge(patient1));
    }

    public Patient getPatientById(Long id) {
        return patientDAO.getByPatientId(id);
    }
}
