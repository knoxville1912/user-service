package com.example.medicine.controller;

import com.example.medicine.converter.ScheduleConverter;
import com.example.medicine.dto.MedicalHistoryDTO;
import com.example.medicine.dto.ScheduleDTO;
import com.example.medicine.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/medicine")
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private ScheduleConverter scheduleConverter;

    @PostMapping("/create-schedule")
    public void createSchedule() {
        scheduleService.createSchedule();
    }

    @GetMapping("/get-schedule-from-date")
    public List<ScheduleDTO> getScheduleFromDate(@RequestParam("localDate")
                                                 @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate localDate) {
        return scheduleService.getScheduleFromDate(localDate).stream()
                .map(scheduleConverter::toDTO)
                .collect(Collectors.toList());
    }

    @PostMapping("/update-schedule-by-id")
    public void updateScheduleById(@RequestParam Long id, Long doctorId) {
        scheduleService.updateScheduleById(id, doctorId);
    }

    @PostMapping("/create-schedule-by-excel")
    public void createScheduleByExcel() throws IOException {
        scheduleService.createScheduleByExcel();
    }

    @GetMapping("/get-example")
    public List<ScheduleDTO> getExample() throws IOException {
        return scheduleService.getListOfScheduleFromExcel().stream()
                .map(scheduleConverter::toDTO)
                .collect(Collectors.toList());
    }
}
