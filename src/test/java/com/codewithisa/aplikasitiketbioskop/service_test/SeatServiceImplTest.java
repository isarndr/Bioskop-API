package com.codewithisa.aplikasitiketbioskop.service_test;

import com.codewithisa.aplikasitiketbioskop.entity.Films;
import com.codewithisa.aplikasitiketbioskop.entity.Schedules;
import com.codewithisa.aplikasitiketbioskop.entity.Seats;
import com.codewithisa.aplikasitiketbioskop.service.FilmServiceImpl;
import com.codewithisa.aplikasitiketbioskop.service.ScheduleServiceImpl;
import com.codewithisa.aplikasitiketbioskop.service.SeatServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SeatServiceImplTest {
    @Autowired
    SeatServiceImpl seatServiceImpl;
    @Autowired
    FilmServiceImpl filmServiceImpl;
    @Autowired
    ScheduleServiceImpl scheduleServiceImpl;
    @Test
    public void printAllAvailableSeatsByScheduleId(){
        seatServiceImpl.printAllAvailableSeatsByScheduleId(1l);
    }
    @Test
    public void pesanTiket(){
        try{
            seatServiceImpl.pesanTiket(1l,"A1");
        }
        catch (Exception e){

        }
    }
    @Test
    public void addSeat(){
        Films films = Films.builder()
                .filmName("test 1")
                .sedangTayang(true)
                .build();
        try{
            filmServiceImpl.addFilm(films);
        }
        catch (Exception e){
            try{
                films=filmServiceImpl.findAllFilmByFilmName("test 1").get(0);
            }
            catch (Exception ex){

            }
        }
        Schedules schedules=Schedules.builder()
                .tanggalTayang("11 Nov 2022")
                .jamSelesai("22.00")
                .jamMulai("20.00")
                .hargaTiket(40000)
                .films(films)
                .build();
        scheduleServiceImpl.addSchedule(schedules);
        Seats seats = Seats.builder()
                .nomorKursi("A1")
                .studioName('A')
                .schedule(schedules)
                .build();
        seatServiceImpl.addSeat(seats);
    }
    @Test
    public void clearTable(){
        seatServiceImpl.clearTable();
    }
}
