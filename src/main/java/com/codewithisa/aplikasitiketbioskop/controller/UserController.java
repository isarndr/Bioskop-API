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
    @GetMapping("{username}")
    public ResponseEntity<Users> getUserByUsername(@PathVariable("username") String username){
        return new ResponseEntity<Users>(userService.getUserByUsername(username),HttpStatus.OK);
    }

}
