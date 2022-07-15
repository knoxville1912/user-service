package com.example.medicine.controller;

import com.example.medicine.converter.DoctorConverter;
import com.example.medicine.dto.DoctorDTO;
import com.example.medicine.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/medicine")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private DoctorConverter doctorConverter;

    @PostMapping("/create-doctor")
    public DoctorDTO createDoctor(@RequestBody DoctorDTO doctorDTO) {
        return doctorConverter.toDTO(doctorService.createDoctor(
                doctorConverter.toModel(doctorDTO))
        );
    }

    @GetMapping("/get-active-doctor-by-patient/{id}")
    public DoctorDTO getActiveDoctorByPatient(@PathVariable Long id) {
        return doctorConverter.toDTO(doctorService.getActiveDoctorByPatient(id));
    }

    @DeleteMapping("/{id}")
    public void quitDoctor(@PathVariable Long id) {
        doctorService.quitDoctor(id);
    }
}
