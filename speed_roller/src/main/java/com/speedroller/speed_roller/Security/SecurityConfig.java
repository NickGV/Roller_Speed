package com.speedroller.speed_roller.Security;

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
            .authorizeHttpRequests((requests) -> requests
                .requestMatchers("/","/home","corporativo/**","registro/**").permitAll() // Permitir acceso a las rutas solo permitidas para todos los usuarios no registrados
                .requestMatchers("/admin/**","/administrador/clases/**","/**").hasRole("ADMINISTRADOR") // Permitir acceso a las rutas solo permitidas para los usuarios con rol ADMIN
                .requestMatchers("/instructor/profile","/calendario/instructorSchedule","/","/home","corporativo/**","registro/**").hasRole("INSTRUCTOR") // Permitir acceso a las rutas solo permitidas para los usuarios con rol INSTRUCTOR
                .requestMatchers("/estudiante/**","/calendarios/estudentSchedule","/","/home","corporativo/**","registro/**").hasRole("ESTUDIANTE") // Permitir acceso a las rutas solo permitidas para los usuarios con rol ESTUDIANTE
                .anyRequest().authenticated() // Requiere autenticación para cualquier otra solicitud
            )
            .formLogin((form) -> form
                .loginPage("/login") //redirige a la pagina de inicio de sesión personalizada
                .permitAll() // Permite acceso a la página de inicio de sesión para todos los usuarios
            )
            .logout((logout) -> logout
                .permitAll() // Permite acceso al cierre de sesión para todos los usuarios
         );
        return http.build(); // Construye el filtro de seguridad
    }
    
}
