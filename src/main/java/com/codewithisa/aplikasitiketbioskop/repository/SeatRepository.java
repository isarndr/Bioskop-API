package com.codewithisa.aplikasitiketbioskop.repository;

import com.codewithisa.aplikasitiketbioskop.entity.Seats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface SeatRepository extends JpaRepository<Seats,Long> {
    /**
     * mengambil semua kursi yang masih tersedia berdasarkan scheduleId dari database
     * @param schedule_id id schedule yang ingin dicari list kursinya yang masih kosong.
     * @return list kursi yang masih kosong (masih dapat dipesan)
     */
    @Query(nativeQuery = true, value = "select * from seats where schedule_id=:schedule_id")
    List<Seats> getAllAvailableSeatsByScheduleId(@Param("schedule_id") Long schedule_id);

    /**
     * mengambil semua kursi yang masih tersedia berdasarkan scheduleId dan nomorKursi dari database
     * @param schedule_id schedule id yang ingin dipesan
     * @param nomor_kursi nomor kursi yang ingin dipesan
     * @return list of Seats yang dihasilkan berdasarkan hasil query berdasarkan schedule id dan nomor kursi
     */
    @Query(nativeQuery = true, value = "select * from seats where schedule_id=:schedule_id and" +
            " nomor_kursi=:nomor_kursi")
    List<Seats> getAllAvailableSeatsByScheduleIdAndNomorKursi(@Param("schedule_id") Long schedule_id,
                                                              @Param("nomor_kursi") String nomor_kursi);

    /**
     * menghapus data dari table seats berdasarkan scheduleId dan nomorKursi
     * @param schedule_id schedule id yang ingin dihapus
     * @param nomor_kursi nomor kursi yang ingin dihapus
     */
    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = " delete from seats where schedule_id=:schedule_id and " +
            "nomor_kursi=:nomor_kursi")
    void deleteRowByScheduleIdAndNomorKursi(@Param("schedule_id") Long schedule_id,
                                            @Param("nomor_kursi") String nomor_kursi);

    @Transactional
    @Modifying
    @Query(
            nativeQuery = true,
            value = "delete from seats where schedule_id = :scheduleId"
    )
    void deleteSeatByScheduleId(@Param("scheduleId") Long scheduleId);
}
