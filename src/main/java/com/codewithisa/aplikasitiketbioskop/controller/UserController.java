package com.codewithisa.aplikasitiketbioskop.controller;

import com.codewithisa.aplikasitiketbioskop.entity.Users;
import com.codewithisa.aplikasitiketbioskop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("add-user")
    public ResponseEntity<Users> addUser(@RequestBody Users user){
        try{
            return new ResponseEntity<Users>(userService.addUser(user), HttpStatus.CREATED);
        }
        catch (Exception e){
            e.getMessage();
        }
        return null;
    }
    @GetMapping("all-users")
    public List<Users> getAllUsers(){
        return userService.getAllUsers();
    }
    @GetMapping("username/{username}")
    public ResponseEntity<Users> getUserByUsername(@PathVariable("username") String username){
        return new ResponseEntity<Users>(userService.getUserByUsername(username),HttpStatus.OK);
    }
    @GetMapping("user-id/{userId}")
    public ResponseEntity<Users> getUserByUserId(@PathVariable("userId") Long userId){
        return new ResponseEntity<>(userService.getUserByUserId(userId), HttpStatus.OK);
    }
    @PutMapping("update-user/{userId}")
    public ResponseEntity<Users> updateUser(@PathVariable("userId") Long userId, @RequestBody Users user){
        return new ResponseEntity<>(userService.updateUser(user, userId),HttpStatus.OK);
    }
    @DeleteMapping("delete-user/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable("userId") Long userId){
        userService.deleteUser(userId);
        return new ResponseEntity<>("User deleted",HttpStatus.OK);
    }
}
