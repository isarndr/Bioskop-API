//package com.codewithisa.aplikasitiketbioskop.configuration;
//
//import com.codewithisa.aplikasitiketbioskop.entity.enumeration.ERole;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
//@Configuration
//public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
//
//    /**
//     * to give access to each role
//     * @param http
//     * @throws Exception
//     */
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests()
//                    .antMatchers("/customer/**").hasRole(ERole.CUSTOMER.name())
//                    .antMatchers("/admin/**").hasRole(ERole.ADMIN.name())
//                    .anyRequest()
//                    .authenticated()
//                    .and()
//                .formLogin()
//                    .loginPage("/login").permitAll()
//                    .and()
//                .logout()
//                    .permitAll()
//                    .and()
//                .httpBasic();
//    }
//}
