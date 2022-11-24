package com.codewithisa.aplikasitiketbioskop.controller;

import com.codewithisa.aplikasitiketbioskop.entity.Role;
import com.codewithisa.aplikasitiketbioskop.entity.Users;
import com.codewithisa.aplikasitiketbioskop.entity.enumeration.ERoles;
import com.codewithisa.aplikasitiketbioskop.entity.request.SignupRequest;
import com.codewithisa.aplikasitiketbioskop.entity.response.MessageResponse;
import com.codewithisa.aplikasitiketbioskop.repository.RoleRepository;
import com.codewithisa.aplikasitiketbioskop.repository.UserRepository;
import com.codewithisa.aplikasitiketbioskop.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    // for password in updateUser
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserRepository usersRepository;

    @Autowired
    RoleRepository roleRepository;

//    @Operation(summary = "untuk menambahkan user baru")
//    @PostMapping("add-user")
//    public ResponseEntity<Users> addUser(@RequestBody Users user){
//        try{
//            return new ResponseEntity<Users>(userService.addUser(user), HttpStatus.CREATED);
//        }
//        catch (Exception e){
//            e.getMessage();
//        }
//        return null;
//    }

//    @Operation(summary = "untuk menampilkan semua user yang sudah terdaftar")
//    @GetMapping("all-users")
//    public List<Users> getAllUsers(){
//        return userService.getAllUsers();
//    }

//    @Operation(summary = "untuk menampilkan user berdasarkan username")
//    @GetMapping("username/{username}")
//    public ResponseEntity<Users> getUserByUsername(@Schema(example = "isarndr")@PathVariable("username") String username){
//        return new ResponseEntity<Users>(userService.getUserByUsername(username),HttpStatus.OK);
//    }

//    @Operation(summary = "untuk menampilkan user berdasarkan user id")
//    @GetMapping("user-id/{userId}")
//    public ResponseEntity<Users> getUserByUserId(@Schema(example = "1")  @PathVariable("userId") Long userId){
//        return new ResponseEntity<>(userService.getUserByUserId(userId), HttpStatus.OK);
//    }

    @Operation(summary = "untuk mengubah user yang sudah terdaftar")
    @PutMapping("update-user/{userId}")
    public ResponseEntity<MessageResponse> updateUser(@Schema(example = "1") @PathVariable("userId") Long userId,
                                            @Valid @RequestBody SignupRequest signupRequest){

//        Boolean usernameExist = usersRepository.existsByUsername(signupRequest.getUsername());
//        if(Boolean.TRUE.equals(usernameExist)) {
//            return ResponseEntity.badRequest()
//                    .body(new MessageResponse("Error: Username is already taken!"));
//        }
//
//        Boolean emailExist = usersRepository.existsByEmailAddress(signupRequest.getEmail());
//        if(Boolean.TRUE.equals(emailExist)) {
//            return ResponseEntity.badRequest()
//                    .body(new MessageResponse("Error: Email is already taken!"));
//        }

        Users users = new Users(signupRequest.getUsername(), signupRequest.getEmail(),
                passwordEncoder.encode(signupRequest.getPassword()));

        Set<String> strRoles = signupRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if(strRoles == null) {
            Role role = roleRepository.findByName(ERoles.CUSTOMER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found"));
            roles.add(role);
        } else {
            strRoles.forEach(role -> {
                Role roles1 = roleRepository.findByName(ERoles.valueOf(role))
                        .orElseThrow(() -> new RuntimeException("Error: Role " + role + " is not found"));
                roles.add(roles1);
            });
        }
        users.setRoles(roles);
//        usersRepository.save(users);
        try{
            userService.updateUser(users, userId);
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(
                    new MessageResponse("username or email already regestered, please input something else")
            );
        }

//        return new ResponseEntity<>(userService.updateUser(user, userId),HttpStatus.OK);
        return ResponseEntity.ok(new MessageResponse("User updated successfully"));
    }

    @Operation(summary = "untuk menghapus user")
    @DeleteMapping("delete-user/{userId}")
    public ResponseEntity<String> deleteUser(@Schema(example = "1")  @PathVariable("userId") Long userId){
        userService.deleteUser(userId);
        return new ResponseEntity<>("User deleted",HttpStatus.OK);
    }
}
