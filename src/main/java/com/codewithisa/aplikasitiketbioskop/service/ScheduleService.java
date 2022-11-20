package com.codewithisa.aplikasitiketbioskop.service;

import com.codewithisa.aplikasitiketbioskop.entity.Schedules;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ScheduleService {

    /**
     * menambah Schedule baru ke database
     * @param schedules objek Schedules yang ingin dimasukkan ke database
     */
    void addSchedule(Schedules schedules);

    /**
     * ngeprint semua jadwal dari film tertentu
     * @param filmName nama film yang ingin diprint jadwal2nya
     * @throws Exception jika film tidak memiliki jadwal maka exception akan dikibarkan
     */
    void printScheduleByFilmName(String filmName) throws Exception;

    /**
     * mengambil semua Schedules dari film tertentu
     * @param filmName nama film yang ingin diambil jadwal2nya
     * @return list Schedules berdasarkan nama film yang diinputkan
     */
    List<Schedules> getSchedulesByFilmName(String filmName);

    /**
     * mengambil Schedules berdasarkan scheduleId dari database
     * @param scheduleId schedule id yang ingin diambil objek Schedulesnya
     * @return objeck Schedules berdasarkan schedule id yang telah diinputkan
     * @throws Exception exception akan dikibarkan jika schedule id yang diinputkan tidak ada di database
     */
    Schedules findScheduleByScheduleId(Long scheduleId) throws Exception;

    /**
     * menghapus semua data yang ada di table Schedules
     */
    void clearTable();

    /**
     * menghapus Schedules berdasarkan scheduleId di database
     * @param scheduleId schedule id yang ingin dihapus datanya dari table schedules
     */
    void deleteScheduleByScheduleId(Long scheduleId);

    /**
     * mengambil semua Schedules berdasarkan filmCode dari database
     * @param filmCode film code yang ingin diambil objek Schedulesnya
     * @return list Schedules berdasarkan film code yang telah diinputkan
     */
    List<Schedules> findAllSchedulesByFilmCode(Long filmCode);

    Schedules findScheduleByJamMulaiAndStudioNameAndTanggalTayangAndFilmCode(
            String jamMulai,
            Character studioName,
            String tanggalTayang,
            Long filmCode
    ) throws Exception;
}
