package com.example.medicine.model;

import com.google.gson.Gson;

import java.time.LocalDate;

public class Schedule {
    private Long id;
    private Long doctorId;
    private LocalDate dutyDate;

    public Schedule(Long id,
                    Long doctorId,
                    LocalDate dutyDate) {
        this.id = id;
        this.doctorId = doctorId;
        this.dutyDate = dutyDate;
    }

    public Schedule(Long doctorId, LocalDate dutyDate) {
        this.doctorId = doctorId;
        this.dutyDate = dutyDate;
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

    public LocalDate getDutyDate() {
        return dutyDate;
    }

    public void setDutyDate(LocalDate dutyDate) {
        this.dutyDate = dutyDate;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
