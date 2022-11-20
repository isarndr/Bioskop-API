package com.codewithisa.aplikasitiketbioskop.service;

import com.codewithisa.aplikasitiketbioskop.entity.Seats;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SeatService {

    /**
     * ngeprint semua kursi yang tersedia berdasarkan scheduleId
     * @param scheduleId schedule id yang ingin dicari kursi2 yang masih tersedianya
     */
    void printAllAvailableSeatsByScheduleId(Long scheduleId);

    /**
     * untuk memesan tiket. tiket yang sudah dipesan tidak dapat dipesan lagi
     * @param scheduleId schedule id yang ingin dipesan
     * @param nomorKursi nomor kursi yang ingin dipesan
     * @throws Exception jika nomor kursi tidak tersedia maka pemesanan akan gagal dilakukan dan tidak ada kursi yang dihapus
     * di table seats
     */
    void pesanTiket(Long scheduleId,String nomorKursi) throws Exception;

    /**
     * untuk menambah kursi baru ke database
     * dapat memiliki banyak jadwal dan
     * tiap jadwal dapat memiliki nomor kursi dan studio yang berbeda.
     * @param seat object Seats yang ingin ditambahkan ke database.
     */
    void addSeat(Seats seat);

    /**
     * menghapus semua data yang ada di table seats
     */
    void clearTable();

    /**
     * untuk mengambil semua kursi yang tersedia berdasarkan scheduleId
     * @param scheduleId schedule id yang ingin dicari daftar kursinya yang masih tersedia
     * @return list Seats yang masih tersedia (yang dapat dipesan)
     */
    List<Seats> getAllAvailableSeatsByScheduleId(Long scheduleId);
}
