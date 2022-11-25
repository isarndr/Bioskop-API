package com.codewithisa.aplikasitiketbioskop.service_test;

import com.codewithisa.aplikasitiketbioskop.entity.Users;
import com.codewithisa.aplikasitiketbioskop.service.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class UserServiceImplTest {
    @Autowired
    UserServiceImpl userServiceImpl;

    @Test
    public void addUser(){
        Users user = Users.builder().username("coba2").emailAddress("coba2@yahoo.com").password("isa1").build();
        try{
            userServiceImpl.addUser(user);
        }
        catch (Exception e){
            log.error(e.getMessage());
//            System.out.println(e.getMessage());
        }
    }
    @Test
    public void printAllUsername(){

        try{
            userServiceImpl.printAllUsername();
        }
        catch (Exception e){

        }
    }
    @Test
    public void deleteUser(){
        try{
            userServiceImpl.deleteUser("isa");
        }
        catch (Exception e){

        }
    }
    @Test
    public void updateUser(){
        try{
            userServiceImpl.updateUser("isa","isa@yahoo.com","isa1",
                    "alex","alex@yahoo.com","alex1");
        }
        catch (Exception e){

        }
    }
    @Test
    public void deleteAll(){
        userServiceImpl.clearTable();
    }
}
