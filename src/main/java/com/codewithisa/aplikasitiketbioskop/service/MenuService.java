package com.codewithisa.aplikasitiketbioskop.service;

import com.codewithisa.aplikasitiketbioskop.entity.Films;
import com.codewithisa.aplikasitiketbioskop.entity.Schedules;
import com.codewithisa.aplikasitiketbioskop.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Service
public class MenuService {
    @Autowired
    UserServiceImpl userServiceImpl;
    @Autowired
    FilmServiceImpl filmServiceImpl;
    @Autowired
    ScheduleServiceImpl scheduleServiceImpl;
//    @Autowired
//    SeatServiceImpl seatServiceImpl;

    public void menu(Scanner scanner){
        System.out.println();
        System.out.println("Aplikasi pemesanan tiket bioskop");
        System.out.println();
        System.out.println("Pilih menu:\n" +
                "1. Masuk\n" +
                "2. Bikin akun baru\n" +
                "0. Tutup aplikasi");

        byte input = scanner.nextByte();
        System.out.println();
        scanner.nextLine();
        switch (input){
            case 1:
                loginActiveUser(scanner);
                menuSetelahLogin(scanner);
                break;
            case 2:
                bikinActiveUser(scanner);
                break;
            case 0:
                System.exit(0);
            default:
                System.out.println("Masukkan input yang benar");
                menu(scanner);
        }
    }

    private void menuSetelahLogin(Scanner scanner) {
        try{
            System.out.println();
            filmServiceImpl.printAllFilmYangSedangTayang();
        }
        catch (Exception e){

        }
        System.out.print("Ketik nama film untuk melihat jadwal (huruf harus sesuai): ");
        String namaFilm = scanner.nextLine();
        System.out.println();

        List<Films> filmList=new ArrayList<>();
        try{
            filmList=filmServiceImpl.findAllFilmByFilmName(namaFilm);
        }
        catch (Exception e){

        }
        Long filmCode=0l;
        for(int i=0;i<filmList.size();i++){
            filmCode=filmList.get(i).getFilmCode();
//            List<Schedules> scheduleList=scheduleServiceImpl.findAllScheduleByFilmCode(filmCode);
//            scheduleList.forEach(scheduleL -> {
//                System.out.println("Film\t\t\t: "+namaFilm);
//                System.out.println("Tanggal tayang\t: "+scheduleL.getTanggalTayang());
//                System.out.println("Jam mulai\t\t: "+scheduleL.getJamMulai());
//                System.out.println("Jam selesai\t\t: "+scheduleL.getJamSelesai());
//                System.out.println("Studio\t\t\t: "+scheduleL.getStudioName());
//                System.out.println("Harga\t\t\t: Rp."+scheduleL.getHargaTiket());
//                System.out.println("Kode jadwal\t\t: "+scheduleL.getScheduleId());
//                System.out.println();
//                System.out.println();
//            });
        }

        System.out.print("Masukkan kode jadwal untuk memilih jadwal: ");

        Long scheduleId=scanner.nextLong();

        System.out.println();

        String studioName;
//        try{
//            studioName=scheduleServiceImpl.findScheduleByScheduleId(scheduleId).getStudioName();
//        }
//        catch (Exception e){
//
//        }

//        seatService.printKursiYangTersedia(scheduleId,studioName);

        System.out.print("Masukkan nama kursi untuk memesan (tulisan harus sesuai): ");

        String nomorKursi = scanner.next();

//        seatService.pesantiket(scheduleId,nomorKursi);

    }

    private void bikinActiveUser(Scanner scanner) {
        Users activeUser = new Users();

        System.out.print("username\t\t: ");
        String username = scanner.nextLine();
        activeUser.setUsername(username);

        System.out.print("email address\t: ");
        String emailAddress = scanner.nextLine();
        activeUser.setEmailAddress(emailAddress);

        System.out.print("password\t\t: ");
        String password = scanner.nextLine();
        activeUser.setPassword(password);

        try{
            userServiceImpl.addUser(activeUser);
            System.out.println("Akun berhasil dibuat");
            menu(scanner);
        }
        catch (Exception e){
            System.out.println("username sudah terdaftar");
            System.out.println("Silahkan coba lagi");
            bikinActiveUser(scanner);
        }
    }

    private void loginActiveUser(Scanner scanner) {
        Users activeUser = new Users();

        System.out.print("username\t:");
        String username = scanner.nextLine();
        activeUser.setUsername(username);

        System.out.print("password\t:");
        String password = scanner.nextLine();
        activeUser.setPassword(password);
        List<Users> activeUserList=new ArrayList<>();
        try{
            activeUserList=userServiceImpl.findAllUserByUsername(username);
        }
        catch (Exception e){

        }
        if(activeUserList.isEmpty()){
            System.out.println("username tidak ditemukan");
            loginActiveUser(scanner);
        }
        String registeredPassword=activeUserList.get(0).getPassword();
        if(registeredPassword.equals(password)){
            System.out.println("Berhasil masuk");
        }
        else {
            System.out.println("Password salah");
            loginActiveUser(scanner);
        }
    }
}
