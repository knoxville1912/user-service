package com.example.medicine.converter;

import com.example.medicine.dto.ScheduleDTO;
import com.example.medicine.model.Schedule;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ScheduleConverter {

    public ScheduleDTO toDTO(Schedule schedule) {
        return new ScheduleDTO(
                schedule.getId(),
                schedule.getDoctorId(),
                schedule.getDutyDate()
        );
    }

//    public List<ScheduleDTO> toDTO(List<Schedule> scheduleList) {
//        List<ScheduleDTO> scheduleDTOList = new ArrayList<>();
//        for (Schedule schedule : scheduleList) {
//            scheduleDTOList.add(new ScheduleDTO(
//                    schedule.getId(),
//                    schedule.getDoctorId(),
//                    schedule.getDutyDate()
//            ));
//        }
//        return scheduleDTOList;
//    }

    public Schedule toModel(ScheduleDTO scheduleDTO) {
        return new Schedule(
                scheduleDTO.getId(),
                scheduleDTO.getDoctorId(),
                scheduleDTO.getDutyDate()
        );
    }
}
