package com.codewithisa.aplikasitiketbioskop.service;

import com.codewithisa.aplikasitiketbioskop.entity.Users;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    /**
     * menambahkan Users baru ke database
     * @param user object Users yang ingin ditambahkan ke database.
     * @return object Users yang ditambahkan ke database.
     * @throws Exception jika Users sudah terdaftar di database maka addUser akan gagal.
     */
    Users addUser(Users user) throws Exception;

    /**
     * untuk mengambil semua Users berdasarkan username dari database
     * @param username username yang ingin diambil dari database
     * @return list Users berdasarkan username yang telah diinput
     * @throws Exception jika username tidak ada di database maka exception akan dikibarkan
     */
    List<Users> findAllUserByUsername(String username) throws Exception;

    /**
     * untuk ngeprint semua username yang ada di database
     * @throws Exception jika username tidak terdaftar di database maka exception akan muncul
     */
    void printAllUsername() throws Exception;

    /**
     * untuk menghapus Users dari database
     * @param username username yang ingin dihapus dari database
     * @throws Exception jika username tidak ditemukan di database maka exception akan muncul
     */
    void deleteUser(String username) throws Exception;

    /**
     * untuk menghapus Users dari database
     * @param userId userId yang ingin dihapus dari database
     */
    void deleteUser(Long userId);

    /**
     * untuk mengubah Users yang ada di database
     * @param username_before username awal
     * @param email_address_before email address awal
     * @param password_before password awal
     * @param username_after username setelah diubah
     * @param email_address_after email address setelah diubah
     * @param password_after password setelah diubah
     * @return Users yang sudah diubah
     * @throws Exception jika Users tidak ditemukan maka tidak akan terjadi apa2
     */
    Users updateUser(String username_before, String email_address_before, String password_before,
                    String username_after, String email_address_after, String password_after) throws Exception;

    /**
     * untuk mengubah Users yang ada di database
     * @param user object Users yang ingin diubah
     * @param userId userId yang ingin diubah
     * @return object Users yang telah diubah
     */
    Users updateUser(Users user, Long userId);

    /**
     * untuk menghapus semua data yang ada di table users
     */
    void clearTable();

    /**
     * untuk mengambil semua Users yang ada di database
     * @return list Users yang ada di database
     */
    List<Users> getAllUsers();

    /**
     * untuk mengambil Users berdasarkan username dari database
     * @param username username yang ingin diambil object Usersnya
     * @return object Users berdasarkan username yang telah diinput
     */
    Users getUserByUsername(String username);

    /**
     * untuk mengambil Users berdasarkan userId dari database
     * @param userId userId yang ingin diambil object Users nya
     * @return object Users berdasarkan userId yang telah diinputkan
     */
    Users getUserByUserId(Long userId);
}
