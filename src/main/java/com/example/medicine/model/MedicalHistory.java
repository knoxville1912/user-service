package com.example.medicine.model;

import com.google.gson.Gson;

import java.time.LocalDate;

public class MedicalHistory {
    private Long id;
    private Long doctorId;
    private Long patientId;
    private String diagnosis;
    private LocalDate startDate;
    private LocalDate endDate;

    public MedicalHistory(Long id,
                          Long doctorId,
                          Long patientId,
                          String diagnosis,
                          LocalDate startDate,
                          LocalDate endDate) {
        this.id = id;
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.diagnosis = diagnosis;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public MedicalHistory(Long doctorId,
                          Long patientId,
                          String diagnosis,
                          LocalDate startDate,
                          LocalDate endDate) {
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.diagnosis = diagnosis;
        this.startDate = startDate;
        this.endDate = endDate;
    }


    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
