package com.codewithisa.aplikasitiketbioskop.service;

import com.codewithisa.aplikasitiketbioskop.entity.UserDetailsImpl;
import com.codewithisa.aplikasitiketbioskop.entity.Users;
import com.codewithisa.aplikasitiketbioskop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = usersRepository.findByUsername(username).orElseGet(null);
        return UserDetailsImpl.build(user);
    }

//    public UserDetails loadUserByEmail(String email){
//        Users user = usersRepository.findByEmail(email);
//        return UserDetailsImpl.build(user);
//    }

}

