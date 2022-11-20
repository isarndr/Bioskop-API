package com.codewithisa.aplikasitiketbioskop.repository;

import com.codewithisa.aplikasitiketbioskop.entity.Schedules;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedules, Long> {

    /**
     * mengambil semua Schedules berdasarkan filmCode dari database
     * @param film_code kode film yang ingin dicari objek Schedulesnya.
     * @return mereturn list Schedules berdasarkan kode film yang diinputkan.
     */
    @Query(nativeQuery = true, value = "select * from schedules where film_code=:film_code")
    List<Schedules> findAllScheduleByFilmCode(@Param("film_code") Long film_code);

    /**
     * mengambil Schedules berdasarkan scheduleId dari database
     * @param scheduleId id schedule yang ingin dicari objek Schedulesnya
     * @return mereturn object Schedules berdasarkan id schedule yang ingin dicari.
     */
    @Query(nativeQuery = true, value = "select * from schedules where schedule_id=:scheduleId")
    Schedules findScheduleByScheduleId(@Param("scheduleId") Long scheduleId);

    @Query(nativeQuery = true, value = " select * from schedules " +
            "where jam_mulai=:jamMulai and studio_name=:studioName and tanggal_tayang=:tanggalTayang and " +
            "film_code=:filmCode")
    Schedules findScheduleByJamMulaiAndStudioNameAndTanggalTayangAndFilmCode(
            @Param("jamMulai") String jamMulai,
            @Param("studioName") Character studioName,
            @Param("tanggalTayang") String tanggalTayang,
            @Param("filmCode") Long filmCode
    );
}
