package com.codewithisa.aplikasitiketbioskop.service;

import com.codewithisa.aplikasitiketbioskop.entity.Seats;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SeatService {

    /**
     * untuk ngeprint semua kursi yang tersedia di database berdasarkan schedule id
     * @param scheduleId schedule id yang ingin dicari kursi2 yang masih tersedianya
     */
    void printAllAvailableSeatsByScheduleId(Long scheduleId);

    /**
     * untuk melakukan pemesanan tiket. tiket/kuris yang telah dipesan akan dihapus dari table seats agar kursi tsb
     * tidak bisa dipesan lagi.
     * @param scheduleId schedule id yang ingin dipesan
     * @param nomorKursi nomor kursi yang ingin dipesan
     * @throws Exception jika nomor kursi tidak tersedia maka pemesanan akan gagal dilakukan dan tidak ada kursi yang dihapus
     * di table seats
     */
    void pesanTiket(Long scheduleId,String nomorKursi) throws Exception;

    /**
     * untuk menambahkan kursi2 yang tersedia ke database untuk suatu film yang sedang tayang. satu film
     * dapat memiliki banyak jadwal dan
     * tiap jadwal dapat memiliki nomor kursi dan studio yang berbeda.
     * @param seat object Seats yang ingin ditambahkan ke database.
     */
    void addSeat(Seats seat);

    /**
     * untuk menghapus data2 di table seats
     */
    void clearTable();

    /**
     * untuk mengambil semua kursi yang masih tersedia untuk suatu schedule id dari database
     * @param scheduleId schedule id yang ingin dicari daftar kursinya yang masih tersedia
     * @return list Seats yang masih tersedia (yang dapat dipesan)
     */
    List<Seats> getAllAvailableSeatsByScheduleId(Long scheduleId);
}
