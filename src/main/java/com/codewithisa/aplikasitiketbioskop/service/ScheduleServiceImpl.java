package com.codewithisa.aplikasitiketbioskop.service;

import com.codewithisa.aplikasitiketbioskop.entity.Films;
import com.codewithisa.aplikasitiketbioskop.entity.Schedules;
import com.codewithisa.aplikasitiketbioskop.exception.ResourceNotFoundException;
import com.codewithisa.aplikasitiketbioskop.repository.FilmRepository;
import com.codewithisa.aplikasitiketbioskop.repository.ScheduleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ScheduleServiceImpl implements ScheduleService{
    @Autowired
    ScheduleRepository scheduleRepository;
    @Autowired
    FilmRepository filmRepository;
    @Override
    public void addSchedule(Schedules schedules) {
        scheduleRepository.save(schedules);
    }

    @Override
    public void printScheduleByFilmName(String filmName) throws Exception{
        List<Films> filmsList=filmRepository.findFilmByFilmName(filmName);
        if(filmsList.isEmpty()){
            log.error("film is not found");
            throw new Exception("Film tidak ditemukan");
        }
        Long filmCode=filmsList.get(0).getFilmCode();
        List<Schedules> schedulesList = scheduleRepository.findAllScheduleByFilmCode(filmCode);
        if(schedulesList.isEmpty()){
            log.error("the film doesn't have a schedule");
            throw new Exception("Film tidak memiliki jadwal tayang");
        }
        schedulesList.forEach(schedulesS -> {
            log.info("Judul film: "+filmName);
            log.info("Tanggal tayang: "+schedulesS.getTanggalTayang());
            log.info("Jam mulai: "+schedulesS.getJamMulai());
            log.info("Jam selesai: "+schedulesS.getJamSelesai());
            log.info("Harga tiket: "+schedulesS.getHargaTiket());
            log.info("Studio: "+schedulesS.getStudioName());
            log.info("Kode jadwal: "+schedulesS.getScheduleId());
            log.info(" ");
//            System.out.println("Judul film: "+filmName+"\n" +
//                    "Tanggal tayang: "+schedulesS.getTanggalTayang()+"\n"+
//                    "Jam mulai: "+schedulesS.getJamMulai()+"\n"+
//                    "Jam selesai: "+schedulesS.getJamSelesai()+"\n"+
//                    "Harga tiket: "+schedulesS.getHargaTiket()+"\n"+
//                    "Studio: "+schedulesS.getStudioName()+"\n" +
//                    "Kode jadwal: "+schedulesS.getScheduleId());
//            System.out.println();
        });
    }

    @Override
    public List<Schedules> getSchedulesByFilmName(String filmName) {
        List<Films> filmsList=filmRepository.findFilmByFilmName(filmName);
        if(filmsList.isEmpty()){
            log.error("film name is not found");
            throw new ResourceNotFoundException("Films","filmName",filmName);
        }
        Long filmCode=filmsList.get(0).getFilmCode();
        List<Schedules> schedulesList = scheduleRepository.findAllScheduleByFilmCode(filmCode);
        if(schedulesList.isEmpty()){
            log.error("film code is not found");
            throw new ResourceNotFoundException("Schedules","filmCode",filmCode);
        }
        return schedulesList;
    }

    @Override
    public Schedules findScheduleByScheduleId(Long scheduleId) throws Exception {
        Schedules schedule = scheduleRepository.findScheduleByScheduleId(scheduleId);
        return schedule;
    }

    @Override
    public void clearTable() {
        log.info("schedules table is clear");
        scheduleRepository.deleteAll();
    }

    @Override
    public void deleteScheduleByScheduleId(Long scheduleId) {
        Schedules schedules=scheduleRepository.findScheduleByScheduleId(scheduleId);
        log.info("schedule successfully deleted");
        scheduleRepository.delete(schedules);
    }

    @Override
    public List<Schedules> findAllSchedulesByFilmCode(Long filmCode) {
        return scheduleRepository.findAllScheduleByFilmCode(filmCode);
    }

    @Override
    public Schedules findScheduleByJamMulaiAndStudioNameAndTanggalTayangAndFilmCode(String jamMulai, Character studioName,
                                                                                    String tanggalTayang, Long filmCode)
    throws Exception{
        Schedules schedules = scheduleRepository.findScheduleByJamMulaiAndStudioNameAndTanggalTayangAndFilmCode(jamMulai,
                studioName,tanggalTayang,filmCode);
        if(schedules.equals(null)){
            log.error("schedule is not found");
            throw new Exception("Schedules tidak ditemukan");
        }
        return schedules;
    }
}
