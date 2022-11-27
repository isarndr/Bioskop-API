package com.codewithisa.aplikasitiketbioskop.service;

import com.codewithisa.aplikasitiketbioskop.entity.Seats;
import com.codewithisa.aplikasitiketbioskop.repository.SeatRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class SeatServiceImpl implements SeatService{
    @Autowired
    SeatRepository seatRepository;
    @Override
    public void printAllAvailableSeatsByScheduleId(Long scheduleId) {
        List<Seats> seatsList=seatRepository.getAllAvailableSeatsByScheduleId(scheduleId);
        if(seatsList.isEmpty()){
            log.error("there is no available seat");
//            System.out.println("Tidak ada kursi yang tersedia");
        }
        seatsList.forEach(seats -> {
            log.info(seats.getNomorKursi());
//            System.out.println(seats.getNomorKursi());
        });
    }

    @Override
    public void pesanTiket(Long scheduleId, String nomorKursi) throws Exception{
        List<Seats> seatsList=seatRepository.getAllAvailableSeatsByScheduleIdAndNomorKursi(scheduleId,nomorKursi);
        if(seatsList.isEmpty()){
            log.error("seat isn't available");
            throw new Exception("Kursi tidak tersedia");
        }
        log.info("seat successfully ordered");
//        System.out.println("Kursi berhasil dipesan");
//        System.out.println();
        seatRepository.deleteRowByScheduleIdAndNomorKursi(scheduleId,nomorKursi);
    }

    @Override
    public void addSeat(Seats seat) {
        log.info("seat successfully added");
        seatRepository.save(seat);
    }

    @Override
    public void clearTable() {
        log.info("seats table is clear");
        seatRepository.deleteAll();
    }

    @Override
    public List<Seats> getAllAvailableSeatsByScheduleId(Long scheduleId) {
        return seatRepository.getAllAvailableSeatsByScheduleId(scheduleId);
    }

    @Override
    public Seats getSeatByScheduleIdAndNomorKursi(Long scheduleId, String nomorKursi) throws Exception {
        return seatRepository.getAllAvailableSeatsByScheduleIdAndNomorKursi(scheduleId,nomorKursi).get(0);
    }

    @Override
    public void deleteSeatByScheduleId(Long scheduleId) {
        seatRepository.deleteSeatByScheduleId(scheduleId);
    }
}
