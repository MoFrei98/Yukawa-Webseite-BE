package com.example.yukawawebseitebe.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable) // CSRF-Schutz deaktivieren
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/**").permitAll() // Erlaubt allen Zugriff auf alle Endpunkte
                        .anyRequest().permitAll() // Weitere Endpunkte auch erlauben
                )
                .formLogin(AbstractHttpConfigurer::disable) // Formular-basierte Authentifizierung deaktivieren
                .httpBasic(AbstractHttpConfigurer::disable); // HTTP-Basic Authentifizierung deaktivieren

        return http.build();
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins(/*"http://localhost:4200"*/"*") // Frontend-Origin erlauben
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*")
                        /*.allowCredentials(true)*/;
            }
        };
    }
}