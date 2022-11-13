package com.codewithisa.aplikasitiketbioskop.service;

import com.codewithisa.aplikasitiketbioskop.entity.Users;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    Users addUser(Users user) throws Exception;
    List<Users> findAllUserByUsername(String username) throws Exception;
    void printAllUsername() throws Exception;
    void deleteUser(String username) throws Exception;
    Users updateUser(String username_before, String email_address_before, String password_before,
                    String username_after, String email_address_after, String password_after) throws Exception;
    void clearTable();
    List<Users> getAllUsers();
    Users getUserByUsername(String username);
}
