package com.example.medicine.converter;

import com.example.medicine.dto.ScheduleDTO;
import com.example.medicine.model.Schedule;
import org.springframework.stereotype.Component;

@Component
public class ScheduleConverter {

    public ScheduleDTO toDTO(Schedule schedule) {
        return new ScheduleDTO(
                schedule.getId(),
                schedule.getDoctorId(),
                schedule.getDutyDate()
        );
    }

    public Schedule toModel(ScheduleDTO scheduleDTO) {
        return new Schedule(
                scheduleDTO.getId(),
                scheduleDTO.getDoctorId(),
                scheduleDTO.getDutyDate()
        );
    }
}
