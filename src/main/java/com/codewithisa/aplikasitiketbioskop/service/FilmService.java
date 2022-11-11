package com.codewithisa.aplikasitiketbioskop.service;

import com.codewithisa.aplikasitiketbioskop.entity.Films;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FilmService {
    List<Films> findAllFilm() throws Exception;
    List<Films> findAllFilmByFilmName(String filmName) throws Exception;
    void addFilm(Films film) throws Exception;
    void updateFilmName(String filmNameBefore, String filmNameAfter) throws Exception;
    void deleteFilm(String filmName) throws Exception;
    void printAllFilmYangSedangTayang() throws Exception;
}
