package com.codewithisa.aplikasitiketbioskop.repository;

import com.codewithisa.aplikasitiketbioskop.entity.Films;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @Repository mean untuk melakukan operasi di database malalui Java
 */
@Repository
public interface FilmRepository extends JpaRepository<Films,Long> {

    /**
     * untuk meretrieve list Films berdasarkan nama film. nativeQuery = true meaning string di value merepresentasikan
     * exact query di database (postgres/PgAdmin).
     * @param film_name nama film yang ingin diambil
     * @return list Films dengan nama film yang ingin dicari
     */
    @Query(nativeQuery = true,value = "select * from films where film_name=:film_name")
    List<Films> findFilmByFilmName(@Param("film_name") String film_name);

    /**
     * untuk meretrieve satu film berdasarkan kode film dari database.
     * @param filmCode kode film yang ingin diambil object Films nya.
     * @return Optional<Films>. Optional karena untuk menghindari null pointer exception.
     */
    @Query(nativeQuery = true, value = "select * from films where film_code=:filmCode")
    Optional<Films> findFilmByFilmCode(@Param("filmCode") Long filmCode);

    /**
     * untuk mengubah nama film di database. di sini digunakan store procedure method di postgres (di bagian call
     * change_film_name dst).
     * @param film_name nama film yang ingin diubah (nama film sebelum diubah)
     * @param film_name_after nama film hasil perubahan (nama film setelah diubah)
     */
    @Query(nativeQuery = true,value = "call change_film_name(:film_name_before, :film_name_after)")
    void updateFilmName(@Param("film_name_before") String film_name, @Param("film_name_after") String film_name_after);

    /**
     * meretrieve list Films yang sedang tayang (sedangTayang = true) dari database.
     * @return
     */
    @Query(nativeQuery = true,value = "select * from films where sedang_tayang = true")
    List<Films> findAllFilmYangSedangTayang();
}
