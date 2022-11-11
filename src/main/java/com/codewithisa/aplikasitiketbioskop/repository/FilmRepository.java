package com.codewithisa.aplikasitiketbioskop.repository;

import com.codewithisa.aplikasitiketbioskop.entity.Films;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilmRepository extends JpaRepository<Films,Long> {
    @Query(nativeQuery = true,value = "select * from films where film_name=:film_name")
    List<Films> findFilmByFilmName(@Param("film_name") String film_name);
    @Query(nativeQuery = true,value = "call change_film_name(:film_name_before, :film_name_after)")
    void updateFilmName(@Param("film_name_before") String film_name, @Param("film_name_after") String film_name_after);
    @Query(nativeQuery = true,value = "select * from films where sedang_tayang = true")
    List<Films> findAllFilmYangSedangTayang();
}
