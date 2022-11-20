package com.codewithisa.aplikasitiketbioskop.service;

import com.codewithisa.aplikasitiketbioskop.entity.Films;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Service untuk menuliskan business logic dan membatasi akses ke database (agar pengguna tidak terhubung langsung ke database
 * agar lebih secure)
 */
@Service
public interface FilmService {

    /**
     * meretrieve list of Films (semua film) dari database.
     * @return list of Films yang ada di database.
     * @throws Exception exception akan dikibarkan saat tidak ada film di database. sebenernya bukan exception sih tapi lebih ke
     * notification kalo di database tidak ada film yang didaftarkan.
     */
    List<Films> findAllFilm() throws Exception;

    /**
     * meretrieve all film (list of Films) dari database berdasarkan nama film
     * @param filmName nama film yang ingin dicari
     * @return list of Films berdasarkan nama film yang dimasukkan
     * @throws Exception jika tidak ada film dengan nama film yang dimasukkan maka exception akan dikibarkan.
     */
    List<Films> findAllFilmByFilmName(String filmName) throws Exception;

    /**
     * untuk menambahkan film baru ke database. film harus unique atau dengan kata lain harus belum terdaftar di database
     * @param film object Film yang ingin dimasukkan ke database
     * @return Films yang dimasukkan (untuk keperluan endpoint)
     * @throws Exception jika film sudah terdaftar di database maka exception akan dikibarkan.
     */
    Films addFilm(Films film) throws Exception;

    /**
     * untuk mengubah nama film yang sudah terdaftar di database. jika film belum terdaftar maka exception akan dikibarkan
     * @param filmNameBefore nama film yang ingin diubah
     * @param filmNameAfter nama film setelah diubah
     * @throws Exception jika nama film belum ada di database maka exception akan dikibarkan.
     */
    void updateFilmName(String filmNameBefore, String filmNameAfter) throws Exception;

    /**
     * untuk mengubah nama film di database. film harus sudah ada di database sebelumnya
     * @param film object Films yang ingin diubah namanya
     * @param filmCode kode film yang ingin diubah namanya
     * @return object Films yang sudah diubah namanya
     */
    Films updateFilmName(Films film, Long filmCode);

    /**
     * menghapus data film berdasarkan nama film di database. film sudah harus terdaftar di database sebelumnya
     * @param filmName nama film yang ingin dihapus dari database
     * @throws Exception exception akan dikibarkan jika film belum terdaftar di database
     */
    void deleteFilm(String filmName) throws Exception;

    /**
     * menghapus film dari database berdasarkan kode film. film sudah harus terdaftar sebelumnya di database
     * @param filmCode kode film yang ingin dihapus dari database
     */
    void deleteFilm(Long filmCode);

    /**
     * print semua film yang sedang tayang di database (sedangTayang = true)
     * @throws Exception jika tidak ada film yang sedang tayang maka exception akan dikibarkan
     */
    void printAllFilmYangSedangTayang() throws Exception;

    /**
     * meretrieve semua Films yang sedang tayang (sedangTayang = true) dari database
     * @return list of Films yang sedang tayang
     */
    List<Films> getAllFilmsYangSedangTayang();

    /**
     * untuk menghapus semua data dari database, database tidak dihapus (isi databasenya aja yang dihapus)
     */
    void clearTable();
}
