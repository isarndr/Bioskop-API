package com.codewithisa.aplikasitiketbioskop.service;

import com.codewithisa.aplikasitiketbioskop.entity.Schedules;
import org.springframework.stereotype.Service;

@Service
public interface ScheduleService {
    void addSchedule(Schedules schedules);
    void printScheduleByFilmName(String filmName) throws Exception;
    Schedules findScheduleByScheduleId(Long scheduleId) throws Exception;
    void clearTable();
}
