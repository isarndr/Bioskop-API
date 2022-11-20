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
     * mengambil semua Films yang ada di database
     * @return list of Films yang ada di database.
     * @throws Exception exception akan dikibarkan saat tidak ada film di database. sebenernya bukan exception sih tapi lebih ke
     * notification kalo di database tidak ada film yang didaftarkan.
     */
    List<Films> findAllFilm() throws Exception;

    /**
     * mengambil semua Films berdasarkan filmName dari database
     * @param filmName nama film yang ingin dicari
     * @return list of Films berdasarkan nama film yang dimasukkan
     * @throws Exception jika tidak ada film dengan nama film yang dimasukkan maka exception akan dikibarkan.
     */
    List<Films> findAllFilmByFilmName(String filmName) throws Exception;

    /**
     * menambahkan film baru ke database
     * @param film object Film yang ingin dimasukkan ke database
     * @return Films yang dimasukkan (untuk keperluan endpoint)
     * @throws Exception jika film sudah terdaftar di database maka exception akan dikibarkan.
     */
    Films addFilm(Films film) throws Exception;

    /**
     * mengubah nama film yang ada di database
     * @param filmNameBefore nama film yang ingin diubah
     * @param filmNameAfter nama film setelah diubah
     * @throws Exception jika nama film belum ada di database maka exception akan dikibarkan.
     */
    void updateFilmName(String filmNameBefore, String filmNameAfter) throws Exception;

    /**
     * mengubah nama film yang ada di database
     * @param film object Films yang ingin diubah namanya
     * @param filmCode kode film yang ingin diubah namanya
     * @return object Films yang sudah diubah namanya
     */
    Films updateFilmName(Films film, Long filmCode);

    /**
     * menghapus film yang ada di database
     * @param filmName nama film yang ingin dihapus dari database
     * @throws Exception exception akan dikibarkan jika film belum terdaftar di database
     */
    void deleteFilm(String filmName) throws Exception;

    /**
     * menghapus film yang ada di database
     * @param filmCode kode film yang ingin dihapus dari database
     */
    void deleteFilm(Long filmCode);

    /**
     * ngeprint semua film yang sedang tayang
     * @throws Exception jika tidak ada film yang sedang tayang maka exception akan dikibarkan
     */
    void printAllFilmYangSedangTayang() throws Exception;

    /**
     * mengambil semua film yang sedang tayang
     * @return list of Films yang sedang tayang
     */
    List<Films> getAllFilmsYangSedangTayang();

    /**
     * menghapus semua data yang ada di table Films
     */
    void clearTable();
}
