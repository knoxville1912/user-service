package com.example.medicine.service;

import com.example.medicine.dao.DoctorDAO;
import com.example.medicine.model.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {

    @Autowired
    private DoctorDAO doctorDAO;

    public Doctor getActiveDoctorByPatient(Long id) {
        return doctorDAO.getActiveDoctorByPatient(id);
    }

    public Doctor createDoctor(Doctor doctor) {
        return doctorDAO.createDoctor(doctor);
    }

    public void quitDoctor(Long id) {
        doctorDAO.quitDoctor(id);
    }

}
