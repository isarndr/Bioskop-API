package com.codewithisa.aplikasitiketbioskop.controller;

import com.codewithisa.aplikasitiketbioskop.entity.Films;
import com.codewithisa.aplikasitiketbioskop.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/films")
public class FilmController {
    @Autowired
    FilmService filmService;

    @PostMapping("add-film")
    public ResponseEntity<Films> addFilm(@RequestBody Films film){
        try{
            return new ResponseEntity<>(filmService.addFilm(film), HttpStatus.CREATED);
        }
        catch (Exception e){
            e.getMessage();
        }
        return null;
    }
    @PutMapping("update-film-name/{filmCode}")
    public ResponseEntity<Films> updateFilmName(@PathVariable("filmCode") Long filmCode, @RequestBody Films film){
        return new ResponseEntity<>(filmService.updateFilmName(film, filmCode),HttpStatus.OK);
    }
    @DeleteMapping("delete-film/{filmCode}")
    public ResponseEntity<String> deleteFilmByFilmCode(@PathVariable("filmCode") Long filmCode){
        filmService.deleteFilm(filmCode);
        return new ResponseEntity<>("Film deleted",HttpStatus.OK);
    }
}
