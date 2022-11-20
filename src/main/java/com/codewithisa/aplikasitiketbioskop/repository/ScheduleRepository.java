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
     * meretrieve list Schedules berdasarkan kode film dari database.
     * @param film_code kode film yang ingin dicari objek Schedulesnya.
     * @return mereturn list Schedules berdasarkan kode film yang diinputkan.
     */
    @Query(nativeQuery = true, value = "select * from schedules where film_code=:film_code")
    List<Schedules> findAllScheduleByFilmCode(@Param("film_code") Long film_code);

    /**
     * meretrieve satu Schedules berdasarkan id schedule dari database.
     * @param scheduleId id schedule yang ingin dicari objek Schedulesnya
     * @return mereturn object Schedules berdasarkan id schedule yang ingin dicari.
     */
    @Query(nativeQuery = true, value = "select * from schedules where schedule_id=:scheduleId")
    Schedules findScheduleByScheduleId(@Param("scheduleId") Long scheduleId);
}
