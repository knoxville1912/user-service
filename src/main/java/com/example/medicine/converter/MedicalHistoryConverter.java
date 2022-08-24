package com.example.medicine.converter;

import com.example.medicine.dto.MedicalHistoryDTO;
import com.example.medicine.model.MedicalHistory;
import org.springframework.stereotype.Component;

@Component
public class MedicalHistoryConverter {
    public MedicalHistoryDTO toDTO(MedicalHistory medicalHistory) {
        return new MedicalHistoryDTO(
                medicalHistory.getId(),
                medicalHistory.getDoctorId(),
                medicalHistory.getPatientId(),
                medicalHistory.getDiagnosis(),
                medicalHistory.getStartDate(),
                medicalHistory.getEndDate()
        );
    }

    public MedicalHistory toModel(MedicalHistoryDTO medicalHistoryDTO) {
        return new MedicalHistory(
                medicalHistoryDTO.getId(),
                medicalHistoryDTO.getDoctorId(),
                medicalHistoryDTO.getPatientId(),
                medicalHistoryDTO.getDiagnosis(),
                medicalHistoryDTO.getStartDate(),
                medicalHistoryDTO.getEndDate()
        );
    }
}

