package com.codewithisa.aplikasitiketbioskop.service;

import com.codewithisa.aplikasitiketbioskop.entity.Films;
import com.codewithisa.aplikasitiketbioskop.repository.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
    public void addFilm(Films film) throws Exception {
        List<Films> filmsList=filmRepository.findFilmByFilmName(film.getFilmName());
        if(filmsList.size()>0){
            throw new Exception("Film sudah ada di daftar film");
        }
        filmRepository.save(film);
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
    public void clearTable() {
        filmRepository.deleteAll();
    }
}
