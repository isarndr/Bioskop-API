package com.codewithisa.aplikasitiketbioskop.service;

import com.codewithisa.aplikasitiketbioskop.entity.Films;
import com.codewithisa.aplikasitiketbioskop.exception.ResourceNotFoundException;
import com.codewithisa.aplikasitiketbioskop.repository.FilmRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class FilmServiceImpl implements FilmService{
    @Autowired
    FilmRepository filmRepository;

    @Override
    public List<Films> findAllFilm() throws Exception {
        List<Films> filmsList = filmRepository.findAll();
        if(filmsList.isEmpty()){
            log.error("the film is not found");
            throw new Exception("Tidak ada film di daftar film");
        }
        return filmsList;
    }

    @Override
    public List<Films> findAllFilmByFilmName(String filmName) throws Exception {
        List<Films> filmsList = filmRepository.findFilmByFilmName(filmName);
        return filmsList;
    }

    @Override
    public Films addFilm(Films film) throws Exception {
        List<Films> filmsList=filmRepository.findFilmByFilmName(film.getFilmName());
        if(filmsList.size()>0){
            log.error("film is already on the list, try another film");
            throw new Exception("Film sudah ada di daftar film");
        }
        filmRepository.save(film);
        log.info("film successfully added");
        return film;
    }

    @Override
    public void updateFilmName(String filmNameBefore, String filmNameAfter) throws Exception {
        List<Films> filmsList=filmRepository.findFilmByFilmName(filmNameBefore);
        if(filmsList.size()==0){
            log.error("film is not found");
            throw new Exception("Film tidak ditemukan");
        }
        log.info("film name is successfully updated");
//        System.out.println("Nama film telah diubah");
        filmRepository.updateFilmName(filmNameBefore,filmNameAfter);
    }

    @Override
    public Films updateFilmName(Films film, Long filmCode) {
        Films existingFilm = filmRepository.findFilmByFilmCode(filmCode).orElseThrow(
                ()->new ResourceNotFoundException("Film","filmCode",filmCode)
        );
        existingFilm.setFilmName(film.getFilmName());
        filmRepository.save(existingFilm);
        log.info("film name is successfully updated");
        return existingFilm;
    }

    @Override
    public void deleteFilm(String filmName) throws Exception {
        List<Films> filmsList=filmRepository.findFilmByFilmName(filmName);
        if(filmsList.size()==0){
            log.error("there is no film with a title "+filmName);
            throw new Exception("Tidak ada film dengan judul "+filmName);
        }
//        System.out.println("Film dengan judul "+filmName+" telah dihapus");
        log.info("film with a title {} is successfully deleted", filmName);
        Films film = filmsList.get(0);
        filmRepository.delete(film);
    }

    @Override
    public void deleteFilm(Long filmCode) {
        Films film = filmRepository.findFilmByFilmCode(filmCode).orElseThrow(
                ()->new ResourceNotFoundException("Film", "filmCode",filmCode)
        );
        log.info("the film is successfully deleted");
        filmRepository.deleteById(filmCode);
    }

    @Override
    public void printAllFilmYangSedangTayang() throws Exception {
        List<Films> filmsList = filmRepository.findAllFilmYangSedangTayang();
        if(filmsList.isEmpty()){
            log.error("there is no film yang sedang tayang");
            throw new Exception("Tidak ada film yang sedang tayang");
        }
//        System.out.println("Film yang sedang tayang:");
        log.info("film yang sedang tayang: ");
        filmsList.forEach(filmF->{
            log.info(filmF.getFilmName());
//            System.out.println(filmF.getFilmName());
        });
    }

    @Override
    public List<Films> getAllFilmsYangSedangTayang() {
        return filmRepository.findAllFilmYangSedangTayang();
    }

    @Override
    public void clearTable() {
        log.info("films table is clear");
        filmRepository.deleteAll();
    }
}
