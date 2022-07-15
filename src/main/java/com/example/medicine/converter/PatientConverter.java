package com.example.medicine.converter;

import com.example.medicine.dto.PatientDTO;
import com.example.medicine.dto.PatientWithMedicalHistoryDTO;
import com.example.medicine.model.Patient;
import org.springframework.stereotype.Component;

@Component
public class PatientConverter {
    public PatientDTO toDTO(Patient patient) {
        return new PatientDTO(patient.getId(),
                patient.getName(),
                patient.getCity(),
                patient.getDateBirth(),
                patient.getNumberOMS());
    }

    public String toDiagnosis(PatientWithMedicalHistoryDTO patientWithMedicalHistoryDTO) {
        return patientWithMedicalHistoryDTO.getDiagnosis();
    }

    public Patient toModel(PatientWithMedicalHistoryDTO patientWithMedicalHistoryDTO) {
        return new Patient(patientWithMedicalHistoryDTO.getId(),
                patientWithMedicalHistoryDTO.getName(),
                patientWithMedicalHistoryDTO.getCity(),
                patientWithMedicalHistoryDTO.getDateBirth(),
                patientWithMedicalHistoryDTO.getNumberOMS());
    }

    public Patient toModel(PatientDTO patientDTO) {
        return new Patient(patientDTO.getId(),
                patientDTO.getName(),
                patientDTO.getCity(),
                patientDTO.getDateBirth(),
                patientDTO.getNumberOMS());
    }
}
