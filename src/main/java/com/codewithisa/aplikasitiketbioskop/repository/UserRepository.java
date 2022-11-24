package com.codewithisa.aplikasitiketbioskop.repository;

import com.codewithisa.aplikasitiketbioskop.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {

    /**
     * mengubah Users yang sudah terdaftar di database
     * @param username_before username awal yang ingin diubah
     * @param email_address_before email address awal yang ingin diubah
     * @param password_before password awawl yang ingin diubah
     * @param username_after username setelah diubah
     * @param email_address_after email address setelah diubah
     * @param password_after password setelah diubah
     */
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

    /**
     * mengambil semua Users berdasarkan username dari database
     * @param username username yang ingin dicari object Usersnya
     * @return list of Users berdasarkan username yang diinputkan
     */
    @Query(nativeQuery = true, value = "select * from users where username=:username")
    List<Users> findAllUserByUsername(@Param("username") String username);

    /**
     * mengambil Users berdasarkan username dari database
     * @param username username yang ingin dicari object Usersnya.
     * @return mereturn object Users berdasarkan username yang telah diinputkan
     */
    @Query(nativeQuery = true, value = "select * from users where username=:username")
    Users getUserByUsername(@Param("username") String username);

    Optional<Users> findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmailAddress(String email);


}
