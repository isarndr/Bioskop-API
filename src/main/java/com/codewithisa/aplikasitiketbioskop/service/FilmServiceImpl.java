package com.codewithisa.aplikasitiketbioskop.service;

import com.codewithisa.aplikasitiketbioskop.entity.Films;
import com.codewithisa.aplikasitiketbioskop.exception.ResourceNotFoundException;
import com.codewithisa.aplikasitiketbioskop.repository.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FilmServiceImpl implements FilmService{
    @Autowired
    FilmRepository filmRepository;

    @Override
    public List<Films> findAllFilm() throws Exception {
        List<Films> filmsList = filmRepository.findAll();
        if(filmsList.isEmpty()){
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
            throw new Exception("Film sudah ada di daftar film");
        }
        filmRepository.save(film);
        return film;
    }

    @Override
    public void updateFilmName(String filmNameBefore, String filmNameAfter) throws Exception {
        List<Films> filmsList=filmRepository.findFilmByFilmName(filmNameBefore);
        if(filmsList.size()==0){
            throw new Exception("Film tidak ditemukan");
        }
        System.out.println("Nama film telah diubah");
        filmRepository.updateFilmName(filmNameBefore,filmNameAfter);
    }

    @Override
    public Films updateFilmName(Films film, Long filmCode) {
        Films existingFilm = filmRepository.findFilmByFilmCode(filmCode).orElseThrow(
                ()->new ResourceNotFoundException("Film","filmCode",filmCode)
        );
        existingFilm.setFilmName(film.getFilmName());
        filmRepository.save(existingFilm);
        return existingFilm;
    }

    @Override
    public void deleteFilm(String filmName) throws Exception {
        List<Films> filmsList=filmRepository.findFilmByFilmName(filmName);
        if(filmsList.size()==0){
            throw new Exception("Tidak ada film dengan judul "+filmName);
        }
        System.out.println("Film dengan judul "+filmName+" telah dihapus");
        Films film = filmsList.get(0);
        filmRepository.delete(film);
    }

    @Override
    public void deleteFilm(Long filmCode) {
        Films film = filmRepository.findFilmByFilmCode(filmCode).orElseThrow(
                ()->new ResourceNotFoundException("Film", "filmCode",filmCode)
        );
        filmRepository.deleteById(filmCode);
    }

    @Override
    public void printAllFilmYangSedangTayang() throws Exception {
        List<Films> filmsList = filmRepository.findAllFilmYangSedangTayang();
        if(filmsList.isEmpty()){
            throw new Exception("Tidak ada film yang sedang tayang");
        }
        System.out.println("Film yang sedang tayang:");
        filmsList.forEach(filmF->{
            System.out.println(filmF.getFilmName());
        });
    }

    @Override
    public List<Films> getAllFilmsYangSedangTayang() {
        return filmRepository.findAllFilmYangSedangTayang();
    }

    @Override
    public void clearTable() {
        filmRepository.deleteAll();
    }
}
