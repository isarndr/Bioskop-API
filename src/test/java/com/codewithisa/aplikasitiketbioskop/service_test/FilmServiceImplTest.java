package com.codewithisa.aplikasitiketbioskop.service_test;

import com.codewithisa.aplikasitiketbioskop.entity.Films;
import com.codewithisa.aplikasitiketbioskop.service.FilmServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@SpringBootTest
public class FilmServiceImplTest {
    @Autowired
    FilmServiceImpl filmServiceImpl;

    @Test
    public void addFilm(){
        Films film = Films.builder().filmName("One Piece Red").sedangTayang(true).build();
        try{
            filmServiceImpl.addFilm(film);
        }
        catch (Exception e){
            log.error(e.getMessage());
//            System.out.println(e.getMessage());
        }
    }
    @Test
    public void updateFilmName(){
        try{
            filmServiceImpl.updateFilmName("Dory","Dory Updated");
        }
        catch (Exception e){
            log.error(e.getMessage());
//            System.out.println(e.getMessage());
        }
    }
    @Test
    public void printAllFilmName(){
        List<Films> filmsList = new ArrayList<>();
        try{
            filmsList = filmServiceImpl.findAllFilm();
        }
        catch (Exception e){

        }
        filmsList.forEach(x->{
            log.info(x.getFilmName());
//            System.out.println(x.getFilmName());
        });
    }
    @Test
    public void deleteFilm(){
        try{
            filmServiceImpl.deleteFilm("Dory Updated");
        }
        catch (Exception e){

        }
    }
    @Test
    public void printAllFilmYangSedangTayang(){
        try{
            filmServiceImpl.printAllFilmYangSedangTayang();
        }
        catch (Exception e){

        }
    }
//    @Test
//    public void deleteAll(){
//        filmServiceImpl.clearTable();
//    }
}
