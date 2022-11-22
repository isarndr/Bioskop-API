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
        String filmName="film name 1";
        Character studioName='A';
        Films films = Films.builder()
                .filmName(filmName)
                .sedangTayang(true)
                .build();
        try{
            filmServiceImpl.addFilm(films);
        }
        catch (Exception e){
            try{
                films=filmServiceImpl.findAllFilmByFilmName(filmName).get(0);
            }
            catch (Exception ex){

            }
        }
        Schedules schedules=Schedules.builder()
                .tanggalTayang("22 Nov 2022")
                .jamMulai("11.00")
                .jamSelesai("13.00")
                .hargaTiket(45000)
                .films(films)
                .studioName(studioName)
                .build();
        scheduleServiceImpl.addSchedule(schedules);
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
        seatServiceImpl.addSeat(seats);

        Seats seats2 = Seats.builder()
                .nomorKursi(studioName+"2")
                .studioName(studioName)
                .scheduleId(scheduleId)
                .build();
        seatServiceImpl.addSeat(seats2);

        Seats seats3 = Seats.builder()
                .nomorKursi(studioName+"3")
                .studioName(studioName)
                .scheduleId(scheduleId)
                .build();
        seatServiceImpl.addSeat(seats3);
    }
    @Test
    public void clearTable(){
        seatServiceImpl.clearTable();
        scheduleServiceImpl.clearTable();
        filmServiceImpl.clearTable();
    }
}
