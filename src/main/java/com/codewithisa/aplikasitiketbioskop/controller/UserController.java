package com.codewithisa.aplikasitiketbioskop.controller;

import com.codewithisa.aplikasitiketbioskop.entity.Users;
import com.codewithisa.aplikasitiketbioskop.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
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

    @Operation(summary = "untuk menambahkan user baru")
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

    @Operation(summary = "untuk menampilkan semua user yang sudah terdaftar")
    @GetMapping("all-users")
    public List<Users> getAllUsers(){
        return userService.getAllUsers();
    }

    @Operation(summary = "untuk menampilkan user berdasarkan username")
    @GetMapping("username/{username}")
    public ResponseEntity<Users> getUserByUsername(@Schema(example = "isarndr")@PathVariable("username") String username){
        return new ResponseEntity<Users>(userService.getUserByUsername(username),HttpStatus.OK);
    }

    @Operation(summary = "untuk menampilkan user berdasarkan user id")
    @GetMapping("user-id/{userId}")
    public ResponseEntity<Users> getUserByUserId(@Schema(example = "1")  @PathVariable("userId") Long userId){
        return new ResponseEntity<>(userService.getUserByUserId(userId), HttpStatus.OK);
    }

    @Operation(summary = "untuk mengubah user yang sudah terdaftar")
    @PutMapping("update-user/{userId}")
    public ResponseEntity<Users> updateUser(@Schema(example = "1") @PathVariable("userId") Long userId,
                                            @RequestBody Users user){
        return new ResponseEntity<>(userService.updateUser(user, userId),HttpStatus.OK);
    }

    @Operation(summary = "untuk menghapus user")
    @DeleteMapping("delete-user/{userId}")
    public ResponseEntity<String> deleteUser(@Schema(example = "1")  @PathVariable("userId") Long userId){
        userService.deleteUser(userId);
        return new ResponseEntity<>("User deleted",HttpStatus.OK);
    }
}
