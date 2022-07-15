package com.example.medicine.service;

import com.example.medicine.dao.MedicalHistoryDAO;
import com.example.medicine.model.MedicalHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicalHistoryService {

    @Autowired
    private MedicalHistoryDAO medicalHistoryDAO;

    public List<MedicalHistory> getAllMedicalHistoryByPatientId(Long id) {
        return medicalHistoryDAO.getAllMedicalHistoryByPatientId(id);
    }

    public List<MedicalHistory> getAllMedicalHistoryByDoctorId(Long id) {
        return medicalHistoryDAO.getAllMedicalHistoryByDoctorId(id);
    }

    public void updateMedicalHistory(MedicalHistory medicalHistory) {
        medicalHistoryDAO.updateMedicalHistory(medicalHistory);
    }
}
