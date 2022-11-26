//package com.codewithisa.aplikasitiketbioskop.service;
//
//import com.codewithisa.aplikasitiketbioskop.entity.Films;
//import com.codewithisa.aplikasitiketbioskop.entity.Schedules;
//import com.codewithisa.aplikasitiketbioskop.entity.Seats;
//import com.codewithisa.aplikasitiketbioskop.entity.Users;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Scanner;
//
//@Service
//public class MenuService {
//    @Autowired
//    UserServiceImpl userServiceImpl;
//    @Autowired
//    FilmServiceImpl filmServiceImpl;
//    @Autowired
//    ScheduleServiceImpl scheduleServiceImpl;
//    @Autowired
//    SeatServiceImpl seatServiceImpl;
//
//    public void menu(Scanner scanner){
//        System.out.println("Pilih menu:\n" +
//                "1. Masuk\n" +
//                "2. Bikin akun baru\n" +
//                "0. Tutup aplikasi");
//
//        byte input = scanner.nextByte();
//        System.out.println();
//        scanner.nextLine();
//        switch (input){
//            case 1:
//                loginActiveUser(scanner);
//                menuSetelahLogin(scanner);
//                break;
//            case 2:
//                bikinActiveUser(scanner);
//                break;
//            case 0:
//                System.exit(0);
//            default:
//                System.out.println("Masukkan input yang benar");
//                menu(scanner);
//        }
//    }
//
//    private void menuSetelahLogin(Scanner scanner) {
//        try{
//            System.out.println();
//            filmServiceImpl.printAllFilmYangSedangTayang();
//        }
//        catch (Exception e){
//
//        }
//        System.out.println();
//        System.out.print("Ketik nama film untuk melihat jadwal (huruf harus sesuai): ");
//        String namaFilm = scanner.nextLine();
//        System.out.println();
//
//        try{
//            scheduleServiceImpl.printScheduleByFilmName(namaFilm);
//        }
//        catch (Exception e){
//
//        }
//
//        System.out.print("Masukkan kode jadwal untuk memilih jadwal: ");
//
//        Long scheduleId=scanner.nextLong();
//
////        Character studioName;
////        try{
////            studioName=scheduleServiceImpl.findScheduleByScheduleId(scheduleId).getStudioName();
////        }
////        catch (Exception e){
////
////        }
//
//        seatServiceImpl.printAllAvailableSeatsByScheduleId(scheduleId);
//        System.out.println();
////        seatService.printKursiYangTersedia(scheduleId,studioName);
//
//        List<Seats> seatsList=seatServiceImpl.getAllAvailableSeatsByScheduleId(scheduleId);
//        if(!seatsList.isEmpty()){
//            System.out.print("Masukkan nama kursi untuk memesan (tulisan harus sesuai): ");
//        }else{
//            scanner.nextLine();
//            menuSetelahLogin(scanner);
//        }
//
//        String nomorKursi = scanner.next();
//
//        try{
//            seatServiceImpl.pesanTiket(scheduleId,nomorKursi);
//        }
//        catch (Exception e){
//
//        }
////        seatService.pesantiket(scheduleId,nomorKursi);
//
//    }
//
//    private void bikinActiveUser(Scanner scanner) {
//        Users activeUser = new Users();
//
//        System.out.print("username\t\t: ");
//        String username = scanner.nextLine();
//        activeUser.setUsername(username);
//
//        System.out.print("email address\t: ");
//        String emailAddress = scanner.nextLine();
//        activeUser.setEmailAddress(emailAddress);
//
//        System.out.print("password\t\t: ");
//        String password = scanner.nextLine();
//        activeUser.setPassword(password);
//
//        try{
//            userServiceImpl.addUser(activeUser);
//            System.out.println("Akun berhasil dibuat");
//            System.out.println();
//            menu(scanner);
//        }
//        catch (Exception e){
//            System.out.println("username sudah terdaftar");
//            System.out.println("Silahkan coba lagi");
//            System.out.println();
//            bikinActiveUser(scanner);
//        }
//    }
//
//    private void loginActiveUser(Scanner scanner) {
//        Users activeUser = new Users();
//
//        System.out.print("username\t: ");
//        String username = scanner.nextLine();
//        activeUser.setUsername(username);
//
//        System.out.print("password\t: ");
//        String password = scanner.nextLine();
//        activeUser.setPassword(password);
//        List<Users> activeUserList=new ArrayList<>();
//        try{
//            activeUserList=userServiceImpl.findAllUserByUsername(username);
//        }
//        catch (Exception e){
//
//        }
//        if(activeUserList.isEmpty()){
//            System.out.println("username tidak ditemukan");
//            loginActiveUser(scanner);
//        }
//        String registeredPassword=activeUserList.get(0).getPassword();
//        if(registeredPassword.equals(password)){
//            System.out.println("Berhasil masuk");
//        }
//        else {
//            System.out.println("Password salah");
//            System.out.println();
//            loginActiveUser(scanner);
//        }
//    }
//}
