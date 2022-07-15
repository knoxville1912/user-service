package com.example.medicine.controller;

import com.example.medicine.converter.MedicalHistoryConverter;
import com.example.medicine.dto.MedicalHistoryDTO;
import com.example.medicine.service.MedicalHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/medicine")
public class MedicalHistoryController {

    @Autowired
    private MedicalHistoryService medicalHistoryService;

    @Autowired
    private MedicalHistoryConverter medicalHistoryConverter;

    @GetMapping("/get-all-medical-history-patient/{id}")
    public List<MedicalHistoryDTO> getAllMedicalHistoryByPatient(@PathVariable Long id) {
        return medicalHistoryService.getAllMedicalHistoryByPatientId(id).stream()
                .map(medicalHistoryConverter::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/get-all-medical-history-doctor/{id}")
    public List<MedicalHistoryDTO> getAllMedicalHistoryByDoctor(@PathVariable Long id) {
        return medicalHistoryService.getAllMedicalHistoryByDoctorId(id).stream()
                .map(medicalHistoryConverter::toDTO)
                .collect(Collectors.toList());
    }

    @PostMapping("/update-medical-history")
    public void updateMedicalHistory(@RequestBody MedicalHistoryDTO medicalHistoryDTO) {
        medicalHistoryService.updateMedicalHistory(medicalHistoryConverter.toModel(medicalHistoryDTO));
    }
}
