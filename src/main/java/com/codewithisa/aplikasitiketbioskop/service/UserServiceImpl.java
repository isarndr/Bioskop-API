package com.codewithisa.aplikasitiketbioskop.service;

import com.codewithisa.aplikasitiketbioskop.entity.Users;
import com.codewithisa.aplikasitiketbioskop.exception.ResourceNotFoundException;
import com.codewithisa.aplikasitiketbioskop.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserRepository userRepository;

    @Override
    public Users addUser(Users user) throws Exception {
        List<Users> usersList=userRepository.findAllUserByUsername(user.getUsername());
        if(usersList.size()>0){
            log.error("username "+user.getUsername()+" telah terdaftar, silahkan coba username lain");
            throw new Exception("username "+user.getUsername()+" telah terdaftar silahkan coba username lain");
        }
        log.info("user successfully added");
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
            log.error("username belum terdaftar");
            throw new Exception("username belum terdaftar");
        }
        return usersList;
    }

    @Override
    public void printAllUsername() throws Exception {
        List<Users> usersList=userRepository.findAll();
        if(usersList.isEmpty()){
            log.error("Tidak ada username yang terdaftar");
            throw new Exception("Tidak ada username yang terdaftar");
        }
        usersList.forEach(x->{
            log.info(x.getUsername());
//            System.out.println(x.getUsername());
        });
    }

    @Override
    public void deleteUser(String username) throws Exception {
        List<Users> usersList = userRepository.findAllUserByUsername(username);
        if(usersList.isEmpty()){
            log.error("Username "+username+" tidak ditemukan");
            throw new Exception("Username "+username+" tidak ditemukan");
        }
        log.info("user succesfully deleted");
        userRepository.delete(usersList.get(0));
    }

    @Override
    public void deleteUser(Long userId) {
        // check whether a user exist in the database or not
        userRepository.findById(userId).orElseThrow(
                ()->new ResourceNotFoundException("User","userId",userId)
        );
        log.info("user succesfully deleted");
        userRepository.deleteById(userId);
    }

    @Override
    public Users updateUser(String username_before, String email_address_before, String password_before,
                           String username_after, String email_address_after, String password_after) throws Exception {
        List<Users> usersList= userRepository.findAllUserByUsername(username_before);
        if(usersList.isEmpty()){
            log.error("User tidak ditemukan");
            throw new Exception("User tidak ditemukan");
        }
//        System.out.println("User updated");
        log.info("user successfully updated");
        userRepository.updateUser(username_before,email_address_before,password_before,
                username_after,email_address_after,password_after);
        return userRepository.getUserByUsername(username_after);
    }

    @Override
    public Users updateUser(Users user, Long userId) {
        Users existingUser = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User","userId",userId)
        );
        existingUser.setUsername(user.getUsername());
        existingUser.setEmailAddress(user.getEmailAddress());
        existingUser.setPassword(user.getPassword());
        existingUser.setRoles(user.getRoles());
        userRepository.save(existingUser);
        log.info("user successfully updated");
        return existingUser;
    }

    @Override
    public void clearTable() {
        log.info("users table is clear");
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

    @Override
    public Users getUserByUserId(Long userId) {
        Optional<Users> user = userRepository.findById(userId);
        if(user.isPresent()){
            return user.get();
        }
        else{
            log.error("userid is not found");
            throw new ResourceNotFoundException("User","userId",userId);
        }
    }
}
