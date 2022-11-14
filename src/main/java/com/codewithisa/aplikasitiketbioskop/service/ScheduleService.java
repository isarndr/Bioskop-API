package com.codewithisa.aplikasitiketbioskop.service;

import com.codewithisa.aplikasitiketbioskop.entity.Schedules;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ScheduleService {
    void addSchedule(Schedules schedules);
    void printScheduleByFilmName(String filmName) throws Exception;
    List<Schedules> getSchedulesByFilmName(String filmName);
    Schedules findScheduleByScheduleId(Long scheduleId) throws Exception;
    void clearTable();
    List<Schedules> findAllSchedulesByFilmCode(Long filmCode);
}
