package com.codewithisa.aplikasitiketbioskop.repository;

import com.codewithisa.aplikasitiketbioskop.entity.Seats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatRepository extends JpaRepository<Seats,Long> {
    /**
     * satu film dapat memiliki banyak schedule, satu schedule memiliki schedule id yang unique dan tiap schedule id
     * memiliki daftar kursi. method getAllAvailableSeatsByScheduleId meretrieve list
     * Seats berdasarkan schedule id dari database. method ini gampangnya mengambil kursi yang masih tersedia untuk
     * suatu film tertentu pada jadwal tertentu.
     * @param schedule_id id schedule yang ingin dicari list kursinya yang masih kosong.
     * @return list kursi yang masih kosong (masih dapat dipesan)
     */
    @Query(nativeQuery = true, value = "select * from seats where schedule_id=:schedule_id")
    List<Seats> getAllAvailableSeatsByScheduleId(@Param("schedule_id") Long schedule_id);

    /**
     * meretrieve list Seats dari database berdasarkan schedule id dan nomor kursi. method ini digunakan untuk
     * melakukan pemesanan tiket.
     * @param schedule_id schedule id yang ingin dipesan
     * @param nomor_kursi nomor kursi yang ingin dipesan
     * @return list of Seats yang dihasilkan berdasarkan hasil query berdasarkan schedule id dan nomor kursi
     */
    @Query(nativeQuery = true, value = "select * from seats where schedule_id=:schedule_id and" +
            " nomor_kursi=:nomor_kursi")
    List<Seats> getAllAvailableSeatsByScheduleIdAndNomorKursi(@Param("schedule_id") Long schedule_id,
                                                              @Param("nomor_kursi") String nomor_kursi);

    /**
     * menghapus satu row data di database berdasarkan schedule id dan nomor kursi. method ini digunakan untuk pemesanan tiket
     * di mana setelah pemesanan tiket dilakukan maka kursi yang dipesan tersebut akan dihapus dari database sehingga
     * kursi yang telah dipesan tersebut tidak akan muncul di list kursi yang tersedia.
     * @param schedule_id schedule id yang ingin dihapus
     * @param nomor_kursi nomor kursi yang ingin dihapus
     */
    @Query(nativeQuery = true, value = " delete from seats where schedule_id=:schedule_id and " +
            "nomor_kursi=:nomor_kursi")
    void deleteRowByScheduleIdAndNomorKursi(@Param("schedule_id") Long schedule_id,
                                            @Param("nomor_kursi") String nomor_kursi);
}
