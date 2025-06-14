package com.speedroller.speed_roller.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager(); 
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .userDetailsService(customUserDetailsService)
            .csrf(csrf -> csrf.disable()) 
            .authorizeHttpRequests((requests) -> requests
                .requestMatchers("/", "/home", "/corporativo/**", "/registro/**", "/login","/eventos", "/css/**", "/js/**", "/images/**").permitAll()

                .requestMatchers("/admin/**", "/administrador/**").hasRole("ADMINISTRADOR")

                .requestMatchers("/instructor/**", "/horarios/instructores").hasAnyRole("INSTRUCTOR", "ADMINISTRADOR")

                .requestMatchers("/estudiantes/**", "/horarios/estudiantes").hasAnyRole("ESTUDIANTE", "ADMINISTRADOR")
                
                .requestMatchers("/horarios/**").hasAnyRole("ADMINISTRADOR", "INSTRUCTOR", "ESTUDIANTE")

                .anyRequest().authenticated()
            )
         
            .formLogin((form) -> form
                .loginPage("/login")
                .defaultSuccessUrl(("/home"), true)
                .permitAll()
            )
        
            .logout((logout) -> logout
                .permitAll()
            );

        return http.build();
    }

}
