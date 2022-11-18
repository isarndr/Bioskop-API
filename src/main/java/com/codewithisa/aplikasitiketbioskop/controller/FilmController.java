package com.codewithisa.aplikasitiketbioskop.controller;

import com.codewithisa.aplikasitiketbioskop.entity.Films;
import com.codewithisa.aplikasitiketbioskop.service.FilmService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/films")
public class FilmController {
    @Autowired
    FilmService filmService;

    @Operation(summary = "untuk menambahkan film baru")
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

    @Operation(summary = "untuk mengubah nama film yang sudah terdaftar")
    @PutMapping("update-film-name/{filmCode}")
    public ResponseEntity<Films> updateFilmName(@Schema(example = "1") @PathVariable("filmCode") Long filmCode, @RequestBody Films film){
        return new ResponseEntity<>(filmService.updateFilmName(film, filmCode),HttpStatus.OK);
    }

    @Operation(summary = "untuk menghapus film")
    @DeleteMapping("delete-film/{filmCode}")
    public ResponseEntity<String> deleteFilmByFilmCode(@Schema(example = "1") @PathVariable("filmCode") Long filmCode){
        filmService.deleteFilm(filmCode);
        return new ResponseEntity<>("Film deleted",HttpStatus.OK);
    }

    @Operation(summary = "untuk menampilkan semua film yang sedang tayang")
    @GetMapping("sedang-tayang")
    public List<Films> getAllFilmYangSedangTayang(){
        return filmService.getAllFilmsYangSedangTayang();
    }
}
