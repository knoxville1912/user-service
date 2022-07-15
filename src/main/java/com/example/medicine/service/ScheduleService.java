package com.example.medicine.service;

import com.example.medicine.dao.ScheduleDAO;
import com.example.medicine.mapper.FileReaderScheduleExcelMapper;
import com.example.medicine.model.Schedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import program.FileWorker;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Service
public class ScheduleService {

    @Autowired
    private ScheduleDAO scheduleDAO;

    public void createSchedule() {
        scheduleDAO.createSchedule();
    }

    public List<Schedule> getScheduleFromDate(LocalDate localDate) {
        return scheduleDAO.getScheduleFromDate(localDate);
    }

    public void updateScheduleById(Long id, Long doctorId) {
        scheduleDAO.updateScheduleById(id, doctorId);
    }

    public List<Schedule> getListOfScheduleFromExcel() throws IOException {
        String fileLocation = "C:\\Users\\Danko\\IdeaProjects\\medical\\medical\\medicine\\scheduleFiles\\Schedule.xls";
        return FileWorker.readFileXLS(fileLocation, new FileReaderScheduleExcelMapper(), true, Schedule.class);
    }

    public void createScheduleByExcel() throws IOException {
        scheduleDAO.createScheduleFromExcel(getListOfScheduleFromExcel());
    }
}
