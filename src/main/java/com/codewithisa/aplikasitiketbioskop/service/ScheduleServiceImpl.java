package com.codewithisa.aplikasitiketbioskop.service;

import com.codewithisa.aplikasitiketbioskop.entity.Films;
import com.codewithisa.aplikasitiketbioskop.entity.Schedules;
import com.codewithisa.aplikasitiketbioskop.repository.FilmRepository;
import com.codewithisa.aplikasitiketbioskop.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
            throw new Exception("Film tidak ditemukan");
        }
        Long filmCode=filmsList.get(0).getFilmCode();
        List<Schedules> schedulesList = scheduleRepository.findAllScheduleByFilmCode(filmCode);
        if(schedulesList.isEmpty()){
            throw new Exception("Film tidak memiliki jadwal tayang");
        }
        schedulesList.forEach(schedulesS -> {
            System.out.println("Judul film: "+filmName+"\n" +
                    "Tanggal tayang: "+schedulesS.getTanggalTayang()+"\n"+
                    "Jam mulai: "+schedulesS.getJamMulai()+"\n"+
                    "Jam selesai: "+schedulesS.getJamSelesai()+"\n"+
                    "Harga tiket: "+schedulesS.getHargaTiket()+"\n"+
                    "Studio: "+schedulesS.getStudioName()+"\n" +
                    "Kode jadwal: "+schedulesS.getScheduleId());
            System.out.println();
        });
    }

    @Override
    public Schedules findScheduleByScheduleId(Long scheduleId) throws Exception {
        Schedules schedule = scheduleRepository.findScheduleByScheduleId(scheduleId);
        return schedule;
    }

    @Override
    public void clearTable() {
        scheduleRepository.deleteAll();
    }

    @Override
    public List<Schedules> findAllSchedulesByFilmCode(Long filmCode) {
        return scheduleRepository.findAllScheduleByFilmCode(filmCode);
    }
}
