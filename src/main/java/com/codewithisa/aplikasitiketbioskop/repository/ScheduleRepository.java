package com.codewithisa.aplikasitiketbioskop.repository;

import com.codewithisa.aplikasitiketbioskop.entity.Schedules;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedules, Long> {
    @Query(nativeQuery = true, value = "select * from schedules where films_film_code=:film_code")
    List<Schedules> findAllScheduleByFilmCode(@Param("film_code") Long film_code);
    @Query(nativeQuery = true, value = "select * from schedules where schedule_id=:scheduleId")
    Schedules findScheduleByScheduleId(@Param("scheduleId") Long scheduleId);
}
