package com.codewithisa.aplikasitiketbioskop.controller;

import com.codewithisa.aplikasitiketbioskop.entity.Films;
import com.codewithisa.aplikasitiketbioskop.entity.Schedules;
import com.codewithisa.aplikasitiketbioskop.entity.Seats;
import com.codewithisa.aplikasitiketbioskop.entity.request.FilmRequest;
import com.codewithisa.aplikasitiketbioskop.service.FilmService;
import com.codewithisa.aplikasitiketbioskop.service.ScheduleService;
import com.codewithisa.aplikasitiketbioskop.service.SeatService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/films")
public class FilmController {
    @Autowired
    FilmService filmService;

    @Autowired
    ScheduleService scheduleService;

    @Autowired
    SeatService seatService;

    @Operation(
            summary = "untuk menambahkan film baru",
            security = { @SecurityRequirement(name = "bearer-key") }
    )
    @PostMapping("add-film")
    public ResponseEntity<Films> addFilm(@RequestBody FilmRequest filmRequest){
        Films film = Films
                .builder()
                .filmName(filmRequest.getFilmName())
                .sedangTayang(filmRequest.getSedangTayang())
                .build();
        // if no schedule then just input the filmName and sedangTayang
        if(
                filmRequest.getStudioName() == null ||
                filmRequest.getHargaTiket() == null ||
                filmRequest.getJamMulai() == null ||
                filmRequest.getJamSelesai() == null ||
                filmRequest.getTanggalTayang() ==  null ||
                filmRequest.getSedangTayang() == false
        ){
            try{
                return new ResponseEntity<>(filmService.addFilm(film), HttpStatus.CREATED);
            }
            catch (Exception e){
                log.error("Film {} is already registered",filmRequest.getFilmName());
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }
        // if there is schedule then
        else{
            String filmName= filmRequest.getFilmName();
            Character studioName=filmRequest.getStudioName();
            Films films = Films.builder()
                    .filmName(filmName)
                    .sedangTayang(true)
                    .build();
            try{
                filmService.addFilm(films);
            }
            catch (Exception e){
                try{
                    films=filmService.findAllFilmByFilmName(filmName).get(0);
                }
                catch (Exception ex){
                }
            }
            Schedules schedules=Schedules.builder()
                    .tanggalTayang(filmRequest.getTanggalTayang())
                    .jamMulai(filmRequest.getJamMulai())
                    .jamSelesai(filmRequest.getJamSelesai())
                    .hargaTiket(filmRequest.getHargaTiket())
                    .films(films)
                    .studioName(studioName)
                    .build();
            scheduleService.addSchedule(schedules);
            log.info("Schedule for {} movie successfully added", filmRequest.getFilmName());
//        Long scheduleId=0l;
            Long scheduleId= schedules.getScheduleId();
//        try{
//            scheduleId=schedules.getScheduleId();
//        }
//        catch (Exception e){
//
//        }
            Seats seats = Seats.builder()
                    .nomorKursi(studioName+"1")
                    .studioName(studioName)
                    .scheduleId(scheduleId)
                    .build();
            seatService.addSeat(seats);

            Seats seats2 = Seats.builder()
                    .nomorKursi(studioName+"2")
                    .studioName(studioName)
                    .scheduleId(scheduleId)
                    .build();
            seatService.addSeat(seats2);

            Seats seats3 = Seats.builder()
                    .nomorKursi(studioName+"3")
                    .studioName(studioName)
                    .scheduleId(scheduleId)
                    .build();
            seatService.addSeat(seats3);
            log.info("Seats for {} movie successfully added", filmRequest.getFilmName());
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Operation(
            summary = "untuk mengubah nama film yang sudah terdaftar",
            security = { @SecurityRequirement(name = "bearer-key") }
    )
    @PutMapping("update-film-name/{filmCode}")
    public ResponseEntity<Films> updateFilmName(@Schema(example = "1") @PathVariable("filmCode") Long filmCode, @RequestBody Films film){
        return new ResponseEntity<>(filmService.updateFilmName(film, filmCode),HttpStatus.OK);
    }

    @Operation(
            summary = "untuk menghapus film",
            security = { @SecurityRequirement(name = "bearer-key") }
    )
    @DeleteMapping("delete-film/{filmCode}")
    public ResponseEntity<String> deleteFilmByFilmCode(@Schema(example = "1") @PathVariable("filmCode") Long filmCode){
        List<Schedules> schedulesList=scheduleService.findAllSchedulesByFilmCode(filmCode);
        schedulesList.forEach(schedules->{
            scheduleService.deleteScheduleByScheduleId(schedules.getScheduleId());
            seatService.deleteSeatByScheduleId(schedules.getScheduleId());
        });
        filmService.deleteFilm(filmCode);
        log.info("Film deleted");
        return new ResponseEntity<>("Film deleted",HttpStatus.OK);
    }

    @Operation(summary = "untuk menampilkan semua film yang sedang tayang")
    @GetMapping("sedang-tayang")
    public List<Films> getAllFilmYangSedangTayang(){
        return filmService.getAllFilmsYangSedangTayang();
    }
}
