package com.codewithisa.aplikasitiketbioskop.repository;

import com.codewithisa.aplikasitiketbioskop.entity.Seats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatRepository extends JpaRepository<Seats,Long> {
    @Query(nativeQuery = true, value = "select * from seats where schedule_id=:schedule_id")
    List<Seats> getAllAvailableSeatsByScheduleId(@Param("schedule_id") Long schedule_id);
    @Query(nativeQuery = true, value = "select * from seats where schedule_id=:schedule_id and" +
            " nomor_kursi=:nomor_kursi")
    List<Seats> getAllAvailableSeatsByScheduleIdAndNomorKursi(@Param("schedule_id") Long schedule_id,
                                                              @Param("nomor_kursi") String nomor_kursi);
    @Query(nativeQuery = true, value = " delete from seats where schedule_id=:schedule_id and " +
            "nomor_kursi=:nomor_kursi")
    void deleteRowByScheduleIdAndNomorKursi(@Param("schedule_id") Long schedule_id,
                                            @Param("nomor_kursi") String nomor_kursi);
}
