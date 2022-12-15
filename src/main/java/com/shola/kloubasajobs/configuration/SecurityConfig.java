package com.shola.kloubasajobs.configuration;

import com.shola.kloubasajobs.model.employer.Employer;
import com.shola.kloubasajobs.repository.EmployerRepository;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Security configurations related to authorization and authentications need of the application using Spring Security Framework.
 * @author  Shola Suleman
 * @version 0.1
 * @since   27.11.2022
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(EmployerRepository empRepo){

        return username -> {
            Employer employer = empRepo.findByUsername(username);
            if (employer != null) {
                return employer;
            }

            throw new UsernameNotFoundException("User "+ username + "not found !");
            //return user;
        } ;

    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        return http
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
                )

                .authorizeRequests()
                .antMatchers("/design", "/orders").hasRole("USER")
                .antMatchers("/userHome/*", "/newjob").authenticated()
                .anyRequest().permitAll()

                .and()
                .formLogin()
                .loginPage("/login")
                //.failureUrl("/login.html?error=true")
                .defaultSuccessUrl("/userHome", true)

                .and()
                .logout()
                .logoutSuccessUrl("/")

                .and()
                .build();
    }

}
