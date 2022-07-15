package com.example.medicine.mapper;

import com.example.medicine.model.Schedule;
import program.ReadMapper;

import java.time.LocalDateTime;

public class FileReaderScheduleExcelMapper implements ReadMapper<Schedule> {
    @Override
    public Schedule map(String[] strings) {
        return new Schedule(
                Long.parseLong(strings[0]),
                LocalDateTime.parse(strings[1]).toLocalDate()
        );
    }
}
