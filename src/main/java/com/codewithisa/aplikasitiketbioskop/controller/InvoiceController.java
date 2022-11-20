package com.codewithisa.aplikasitiketbioskop.controller;

import com.codewithisa.aplikasitiketbioskop.entity.Films;
import com.codewithisa.aplikasitiketbioskop.entity.Schedules;
import com.codewithisa.aplikasitiketbioskop.entity.Seats;
import com.codewithisa.aplikasitiketbioskop.entity.Users;
import com.codewithisa.aplikasitiketbioskop.service.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/invoice")
public class InvoiceController {
    @Autowired
    InvoiceService invoiceService;
    @Autowired
    FilmService filmService;

    @Autowired
    ScheduleService scheduleService;

    @Autowired
    UserService userService;

    @Autowired
    SeatService seatService;

    @Operation(summary = "untuk membuat tiket dalam bentuk pdf")
    @PostMapping("generate-tiket")
    public void generateTiket(HttpServletResponse response,
                              @Schema(example = "Nemo") @RequestParam("filmName") String filmName,
                              @Schema(example = "18 November 2022") @RequestParam("tanggalTayang") String tanggalTayang,
                              @Schema(example = "20.00 WIB") @RequestParam("jamMulai") String jamMulai,
                              @Schema(example = "A3") @RequestParam("nomorKursi") String nomorKursi,
                              @Schema(example = "isarndr") @RequestParam("username") String username){

        // cek username
        Users user=null;
        try {
            user=userService.getUserByUsername(username);
        } catch (Exception e) {

        }
        if(user==null){
            System.out.println("username belum terdaftar");
            return; //exit endpoint
        }


        // ambil nama studio dari input nomorKursi
        Character studioName = nomorKursi.charAt(0);

        // ambil filmCode dari input filmName
        Long filmCode=null;
        try {
            filmCode = filmService.findAllFilmByFilmName(filmName).get(0).getFilmCode();
        } catch (Exception e) {

        }

        if(filmCode==null){
            System.out.println("film tidak tersedia");
            return;
        }

        Films films=null;
        try {
            films = filmService.findAllFilmByFilmName(filmName).get(0);
        } catch (Exception e) {

        }

        if(!films.isSedangTayang()){
            System.out.println("film sedang tidak tayang");
            return;
        }

        // find schedules berdasarkan kriteria input endpoint
        Schedules schedules = null;

        try {
            schedules= scheduleService.findScheduleByJamMulaiAndStudioNameAndTanggalTayangAndFilmCode(
                    jamMulai,studioName,tanggalTayang,filmCode
            );
        } catch (Exception e) {

        }
        if(schedules== null){
            System.out.println("Schedules tidak ditemukan");
            return;         // exit end point
        }
//        System.out.println("Schedules ditemukan");

        // pesanTiket preparation

        Long scheduleId = schedules.getScheduleId();

        Seats seat=null;
        try {
            seat=seatService.getSeatByScheduleIdAndNomorKursi(scheduleId,nomorKursi);
        } catch (Exception e) {

        }
        if(seat==null){
            System.out.println("kursi tidak tersedia");
            return; // exit end point
        }
//        System.out.println("kursi tersedia");

        // at this line kursi tersedia, film sedang tayang dan film ada di database, so masuk ke method pesanTiket

        try {
            seatService.pesanTiket(scheduleId,nomorKursi);
        } catch (Exception e) {

        }

        try{
            JasperReport sourceFileName = JasperCompileManager.compileReport(
                    ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX+"tiket.jrxml")
                            .getAbsolutePath()
            );
            List<Map<String,String>> dataPemesan = new ArrayList<>();
            Map<String, String> pemesan = new HashMap<>();
            pemesan.put("filmName", filmName);
            pemesan.put("tanggalTayang", tanggalTayang);
            pemesan.put("jamMulai", jamMulai);
            pemesan.put("nomorKursi", nomorKursi);
            pemesan.put("username", username);
            dataPemesan.add(pemesan);

            JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(dataPemesan);
            Map<String,Object> parameters = new HashMap<>();

            JasperPrint jasperPrint = JasperFillManager.fillReport(sourceFileName, parameters, beanColDataSource);

            response.setContentType("application/pdf");
            response.addHeader("Content-Disposition", "inline; filename=tiket.pdf;");

            JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());


        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
