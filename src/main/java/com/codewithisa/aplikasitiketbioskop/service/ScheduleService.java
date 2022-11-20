package com.codewithisa.aplikasitiketbioskop.service;

import com.codewithisa.aplikasitiketbioskop.entity.Schedules;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ScheduleService {

    /**
     * untuk menambah jadwal baru ke database
     * @param schedules objek Schedules yang ingin dimasukkan ke database
     */
    void addSchedule(Schedules schedules);

    /**
     * untuk ngeprint jadwal2 dari suatu film yang ada di database
     * @param filmName nama film yang ingin diprint jadwal2nya
     * @throws Exception jika film tidak memiliki jadwal maka exception akan dikibarkan
     */
    void printScheduleByFilmName(String filmName) throws Exception;

    /**
     * untuk mengambil schedule2 berdasarkan nama film dari database
     * @param filmName nama film yang ingin diambil jadwal2nya
     * @return list Schedules berdasarkan nama film yang diinputkan
     */
    List<Schedules> getSchedulesByFilmName(String filmName);

    /**
     * untuk mengambil schedule berdasarkan schedule id dari database
     * @param scheduleId schedule id yang ingin diambil objek Schedulesnya
     * @return objeck Schedules berdasarkan schedule id yang telah diinputkan
     * @throws Exception exception akan dikibarkan jika schedule id yang diinputkan tidak ada di database
     */
    Schedules findScheduleByScheduleId(Long scheduleId) throws Exception;

    /**
     * untuk menghapus semua data dari table Schedules
     */
    void clearTable();

    /**
     * untuk menghapus data dari table Schedules berdasarkan schedule id
     * @param scheduleId schedule id yang ingin dihapus datanya dari table schedules
     */
    void deleteScheduleByScheduleId(Long scheduleId);

    /**
     * untuk mengambil semua Schedules di database berdasarkan film code
     * @param filmCode film code yang ingin diambil objek Schedulesnya
     * @return list Schedules berdasarkan film code yang telah diinputkan
     */
    List<Schedules> findAllSchedulesByFilmCode(Long filmCode);
}
