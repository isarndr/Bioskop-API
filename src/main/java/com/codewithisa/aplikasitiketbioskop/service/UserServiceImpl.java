package com.codewithisa.aplikasitiketbioskop.service;

import com.codewithisa.aplikasitiketbioskop.entity.Users;
import com.codewithisa.aplikasitiketbioskop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserRepository userRepository;

    @Override
    public Users addUser(Users user) throws Exception {
        List<Users> usersList=userRepository.findAllUserByUsername(user.getUsername());
        if(usersList.size()>0){
            throw new Exception("username "+user.getUsername()+" telah terdaftar");
        }
        userRepository.save(user);
        return user;
    }

    @Override
    public List<Users> findAllUserByUsername(String username) throws Exception {
        List<Users> usersList;
        try{
            usersList=userRepository.findAllUserByUsername("isa");
        }
        catch (Exception e){
            throw new Exception("username belum terdaftar");
        }
        return usersList;
    }

    @Override
    public void printAllUsername() throws Exception {
        List<Users> usersList=userRepository.findAll();
        if(usersList.isEmpty()){
            throw new Exception("Tidak ada username yang terdaftar");
        }
        usersList.forEach(x->{
            System.out.println(x.getUsername());
        });
    }

    @Override
    public void deleteUser(String username) throws Exception {
        List<Users> usersList = userRepository.findAllUserByUsername(username);
        if(usersList.isEmpty()){
            throw new Exception("Username "+username+" tidak ditemukan");
        }
        userRepository.delete(usersList.get(0));
    }

    @Override
    public Users updateUser(String username_before, String email_address_before, String password_before,
                           String username_after, String email_address_after, String password_after) throws Exception {
        List<Users> usersList= userRepository.findAllUserByUsername(username_before);
        if(usersList.isEmpty()){
            throw new Exception("User tidak ditemukan");
        }
        System.out.println("User updated");
        userRepository.updateUser(username_before,email_address_before,password_before,
                username_after,email_address_after,password_after);
        return userRepository.getUserByUsername(username_after);
    }

    @Override
    public void clearTable() {
        userRepository.deleteAll();
    }

    @Override
    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Users getUserByUsername(String username) {
        Optional<Users> user = Optional.ofNullable(userRepository.getUserByUsername(username));
        if(user.isPresent()){
            return user.get();
        }
        return null;
    }
}
