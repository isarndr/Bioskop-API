package com.codewithisa.aplikasitiketbioskop.service;

import com.codewithisa.aplikasitiketbioskop.entity.Seats;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SeatService {
    void printAllAvailableSeatsByScheduleId(Long scheduleId);
    void pesanTiket(Long scheduleId,String nomorKursi) throws Exception;
    void addSeat(Seats seat);
}
