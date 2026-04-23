package com.escola.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.List;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        
        // ✅ Permitir origins específicos (mais seguro que "*")
        config.setAllowedOrigins(List.of(
            "http://localhost:8081",    // Expo Web
            "http://localhost:19000",   // Expo Go Web
            "http://127.0.0.1:8081",
            "http://127.0.0.1:19000"
        ));
        
        // OU use patterns se precisar de mais flexibilidade:
        // config.setAllowedOriginPatterns(List.of("*"));
        
        config.setAllowedMethods(List.of(
            "GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH", "HEAD"
        ));
        
        config.setAllowedHeaders(List.of("*"));
        config.setExposedHeaders(List.of("Content-Type", "Authorization"));
        
        // ✅ IMPORTANTE: false quando usar allowedOrigins
        config.setAllowCredentials(false);
        
        config.setMaxAge(3600L);
        
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        
        return new CorsFilter(source);
    }
}