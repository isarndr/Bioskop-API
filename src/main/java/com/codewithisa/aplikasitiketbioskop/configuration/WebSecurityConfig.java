package com.codewithisa.aplikasitiketbioskop.configuration;

import com.codewithisa.aplikasitiketbioskop.entity.enumeration.ERoles;
import com.codewithisa.aplikasitiketbioskop.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    private AuthEntryPointJwt unauthorizedHandler;

    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter() {
        return new AuthTokenFilter();
    }

    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception{
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                .antMatchers(
                        "/api/auth/**",
//                        "/users/add-user",
                        "/films/sedang-tayang",
                        "/schedules/**"
//                        "/swagger-ui/**",
//                        "/v3/api-docs/**"
                ).permitAll()
                .antMatchers(
                        "/swagger-ui/index.html",
                        "/swagger-ui.html",
                        "/v3/api-docs/**").permitAll()
                .antMatchers("/api/auth/**").permitAll()
                .antMatchers(
                        "/films/add-film",
                        "/films/update-film-name/**",
                        "/films/delete-film/**"
                ).hasAuthority(ERoles.ADMIN.name())
                .antMatchers(
                        "/users/update-user/**",
                        "/users/delete-user/**",
                        "/invoice/generate-tiket"
                ).hasAuthority(ERoles.CUSTOMER.name())
//                .antMatchers("/files/**").hasAuthority(ERoles.ADMIN.name())
//                .antMatchers("/api/test/**").permitAll()
                .anyRequest().authenticated();

        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    }

//    @Override
//    public void configure(final WebSecurity web) throws Exception {
//        web.ignoring().antMatchers("/**");
//    }
}

