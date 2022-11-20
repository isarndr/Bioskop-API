package com.codewithisa.aplikasitiketbioskop.repository;

import com.codewithisa.aplikasitiketbioskop.entity.Films;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

// layer repository digunakan untuk menghubungkan java dengan database secara langsung

@Repository
public interface FilmRepository extends JpaRepository<Films,Long> {

    /**
     * mengambil semua Films berdasarkan filmName dari database
     * @param film_name nama film yang ingin diambil objek2 Films nya dari database
     * @return list Films yang memiliki nama film yang sesuai
     */
    @Query(nativeQuery = true,value = "select * from films where film_name=:film_name")
    List<Films> findFilmByFilmName(@Param("film_name") String film_name);

    /**
     * mengambil Films berdasarkan filmCode dari database
     * @param filmCode kode film yang ingin diambil object Films nya.
     * @return Optional<Films>. Optional karena untuk menghindari null pointer exception.
     */
    @Query(nativeQuery = true, value = "select * from films where film_code=:filmCode")
    Optional<Films> findFilmByFilmCode(@Param("filmCode") Long filmCode);

    /**
     * mengubah filmName yang ada di database. filmName harus sudah terdaftar di database
     * @param film_name nama film yang ingin diubah (nama film sebelum diubah)
     * @param film_name_after nama film hasil perubahan (nama film setelah diubah)
     */
    @Query(nativeQuery = true,value = "call change_film_name(:film_name_before, :film_name_after)")
    void updateFilmName(@Param("film_name_before") String film_name, @Param("film_name_after") String film_name_after);

    /**
     * mengambil semua Films yang sedang tayang
     * @return
     */
    @Query(nativeQuery = true,value = "select * from films where sedang_tayang = true")
    List<Films> findAllFilmYangSedangTayang();
}
