package com.codewithisa.aplikasitiketbioskop.repository;

import com.codewithisa.aplikasitiketbioskop.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<Users, String> {
    @Query(nativeQuery = true, value = "call change_active_user(:username_before," +
            ":email_address_before," +
            ":password_before," +
            ":username_after," +
            ":email_address_after," +
            ":password_after)")
    void updateUser(@Param("username_before") String username_before,
                    @Param("email_address_before") String email_address_before,
                    @Param("password_before") String password_before,
                    @Param("username_after") String username_after,
                    @Param("email_address_after") String email_address_after,
                    @Param("password_after") String password_after);
    @Query(nativeQuery = true, value = "select * from users where username=:username")
    List<Users> findAllUserByUsername(@Param("username") String username);
}
