package com.example.medicine.controller;

import com.example.medicine.converter.PatientConverter;
import com.example.medicine.dto.PatientDTO;
import com.example.medicine.dto.PatientWithMedicalHistoryDTO;
import com.example.medicine.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/medicine")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @Autowired
    private PatientConverter patientConverter;

    @PostMapping("/create-patient")
    public PatientDTO createPatient(@RequestBody PatientWithMedicalHistoryDTO patientWithMedicalHistoryDTO) {
        return patientConverter.toDTO(
                patientService.createPatientWithMedicalHistory(
                        patientConverter.toModel(patientWithMedicalHistoryDTO),
                        patientConverter.toDiagnosis(patientWithMedicalHistoryDTO)));
    }

    @PostMapping("/discharge-patient")
    public void dischargePatient(@RequestBody PatientDTO patientDTO) {
        patientService.patientDischarge(patientConverter.toModel(patientDTO));
    }

    @GetMapping("/get-all-patients-by-doctors-id/{id}")
    public List<PatientDTO> getAllPatientsByDoctorsId(@PathVariable Long id) {
        return patientService.getAllPatientByDoctorId(id).stream()
                .map(patientConverter::toDTO)
                .collect(Collectors.toList());
    }

}
