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
    private CustomUserDetailsService customUserDetailsService; // Inyectamos el servicio de detalles de usuario personalizado
    // Este servicio se encargará de cargar los detalles del usuario desde la base de datos

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();// Utilizamos BCrypt para codificar las contraseñas
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager(); // Configuramos el administrador de autenticación
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .userDetailsService(customUserDetailsService)
            .csrf(csrf -> csrf.disable()) // Deshabilitamos CSRF para permitir POST sin token
            .authorizeHttpRequests((requests) -> requests
                // Rutas públicas (acceso sin login)
                .requestMatchers("/", "/home", "/corporativo/**", "/registro/**", "/login","/eventos", "/css/**", "/js/**", "/images/**").permitAll()

                // Rutas para ADMINISTRADOR (acceso total a todo lo que no sea público)
                .requestMatchers("/admin/**", "/administrador/**").hasRole("ADMINISTRADOR")

                // Rutas específicas para INSTRUCTOR
                .requestMatchers("/instructor/**", "/horarios/instructores").hasAnyRole("INSTRUCTOR", "ADMINISTRADOR")

                // Rutas específicas para ESTUDIANTE
                .requestMatchers("/estudiantes/**", "/horarios/estudiantes").hasAnyRole("ESTUDIANTE", "ADMINISTRADOR")
                
                // Rutas de horarios generales
                .requestMatchers("/horarios/**").hasAnyRole("ADMINISTRADOR", "INSTRUCTOR", "ESTUDIANTE")

                // Cualquier otra ruta requiere autenticación
                .anyRequest().authenticated()
            )
            // Config login
            .formLogin((form) -> form
                .loginPage("/login")
                .defaultSuccessUrl(("/home"), true)
                .permitAll()
            )
            // Config logout
            .logout((logout) -> logout
                .permitAll()
            );

        return http.build();
    }

}
