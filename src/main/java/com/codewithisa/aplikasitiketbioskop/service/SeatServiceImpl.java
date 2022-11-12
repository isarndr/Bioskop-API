package com.codewithisa.aplikasitiketbioskop.service;

import com.codewithisa.aplikasitiketbioskop.entity.Seats;
import com.codewithisa.aplikasitiketbioskop.repository.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatServiceImpl implements SeatService{
    @Autowired
    SeatRepository seatRepository;
    @Override
    public void printAllAvailableSeatsByScheduleId(Long scheduleId) {
        List<Seats> seatsList=seatRepository.getAllAvailableSeatsByScheduleId(scheduleId);
        if(seatsList.isEmpty()){
            System.out.println("Tidak ada kursi yang tersedia");
        }
        seatsList.forEach(seats -> {
            System.out.println(seats.getNomorKursi());
        });
    }

    @Override
    public void pesanTiket(Long scheduleId, String nomorKursi) throws Exception{
        List<Seats> seatsList=seatRepository.getAllAvailableSeatsByScheduleIdAndNomorKursi(scheduleId,nomorKursi);
        if(seatsList.isEmpty()){
            throw new Exception("Kursi tidak tersedia");
        }
        System.out.println("Kursi telah dipesan");
        System.out.println();
        seatRepository.deleteRowByScheduleIdAndNomorKursi(scheduleId,nomorKursi);
    }

    @Override
    public void addSeat(Seats seat) {
        seatRepository.save(seat);
    }

    @Override
    public void clearTable() {
        seatRepository.deleteAll();
    }

    @Override
    public List<Seats> getAllAvailableSeatsByScheduleId(Long scheduleId) {
        return seatRepository.getAllAvailableSeatsByScheduleId(scheduleId);
    }
}
