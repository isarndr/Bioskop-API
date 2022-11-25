package com.codewithisa.aplikasitiketbioskop.service_test;

import com.codewithisa.aplikasitiketbioskop.entity.Films;
import com.codewithisa.aplikasitiketbioskop.entity.Schedules;
import com.codewithisa.aplikasitiketbioskop.service.FilmServiceImpl;
import com.codewithisa.aplikasitiketbioskop.service.ScheduleServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ScheduleServiceImplTest {
    @Autowired
    ScheduleServiceImpl scheduleServiceImpl;
    @Autowired
    FilmServiceImpl filmServiceImpl;
    @Test
    void addSchedule(){
        Films film = Films.builder()
                .sedangTayang(true)
                .filmName("film schedule")
                .build();
        try{
            filmServiceImpl.addFilm(film);
        }
        catch (Exception e){
            try{
                film = filmServiceImpl.findAllFilmByFilmName("film schedule").get(0);
            }
            catch (Exception ex){

            }
        }
        Schedules schedules = Schedules.builder()
                .films(film)
                .hargaTiket(50000)
                .jamMulai("20.00")
                .jamSelesai("22.30")
                .studioName('A')
                .tanggalTayang("12 Nov 2022")
                .build();
        scheduleServiceImpl.addSchedule(schedules);
    }
    @Test
    void addSecondsSchedule(){
        Films film;
        try{
            film = filmServiceImpl.findAllFilmByFilmName("film schedule").get(0);
            Schedules schedules = Schedules.builder()
                    .films(film)
                    .hargaTiket(45000)
                    .jamMulai("21.00")
                    .studioName('B')
                    .jamSelesai("23.30")
                    .tanggalTayang("13 Nov 2022")
                    .build();
            scheduleServiceImpl.addSchedule(schedules);
        }
        catch (Exception e){

        }
    }
    @Test
    void addThirdSchedule(){
        Films film;
        try{
            film = filmServiceImpl.findAllFilmByFilmName("film schedule").get(0);
            Schedules schedules = Schedules.builder()
                    .films(film)
                    .hargaTiket(40000)
                    .jamMulai("13.00")
                    .jamSelesai("15.30")
                    .tanggalTayang("13 Nov 2022")
                    .build();
            scheduleServiceImpl.addSchedule(schedules);
        }
        catch (Exception e){

        }
    }
    @Test
    void printScheduleByFilmName(){
        try{
            scheduleServiceImpl.printScheduleByFilmName("Pengabdi Mantan");
        }
        catch (Exception e){

        }
    }
//    @Test
//    public void deleteAll(){
//        scheduleServiceImpl.clearTable();
//    }
}
