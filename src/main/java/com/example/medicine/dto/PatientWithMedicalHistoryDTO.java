package com.example.medicine.dto;

import java.time.LocalDate;

public class PatientWithMedicalHistoryDTO {
    private Long id;
    private String name;
    private String city;
    private LocalDate dateBirth;
    private String numberOMS;
    private String diagnosis;

    public PatientWithMedicalHistoryDTO() {
    }

    public PatientWithMedicalHistoryDTO(Long id, String name, String city, LocalDate dateBirth, String numberOMS, String diagnosis) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.dateBirth = dateBirth;
        this.numberOMS = numberOMS;
        this.diagnosis = diagnosis;
    }

    public PatientWithMedicalHistoryDTO(String name, String city, LocalDate dateBirth, String numberOMS, String diagnosis) {
        this.name = name;
        this.city = city;
        this.dateBirth = dateBirth;
        this.numberOMS = numberOMS;
        this.diagnosis = diagnosis;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public LocalDate getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(LocalDate dateBirth) {
        this.dateBirth = dateBirth;
    }

    public String getNumberOMS() {
        return numberOMS;
    }

    public void setNumberOMS(String numberOMS) {
        this.numberOMS = numberOMS;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }
}
