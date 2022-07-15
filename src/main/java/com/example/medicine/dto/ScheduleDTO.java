package com.example.medicine.dto;

import java.time.LocalDate;

public class ScheduleDTO {
    private Long id;
    private Long doctorId;
    private LocalDate dutyDate;

    public ScheduleDTO() {
    }

    public ScheduleDTO(Long id, Long doctorId, LocalDate dutyDate) {
        this.id = id;
        this.doctorId = doctorId;
        this.dutyDate = dutyDate;
    }

    public ScheduleDTO(Long doctorId, LocalDate dutyDate) {
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
}
