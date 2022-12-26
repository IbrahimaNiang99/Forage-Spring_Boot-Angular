package com.example.jwt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.*;
@SpringBootApplication
public class JwtApplication {

    public static void main(String[] args) {
        SpringApplication.run(JwtApplication.class, args);
    }



    @Configuration
    public class CorsConfig {
        @Bean
        public CorsFilter corsFilter() {
            final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
            final CorsConfiguration config = new CorsConfiguration();
            config.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
            config.setAllowedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Access-Control-Allow-Headers", "X-Requested-With,content-type"));
            config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "OPTIONS", "DELETE", "PATCH"));
            config.setAllowCredentials(true);
            source.registerCorsConfiguration("/**", config);
            return new CorsFilter(source);
        }
    }
 /*
  @Bean
  CommandLineRunner run(UserService userService){
        return args -> {

            userService.saveUser(new User(1, "Ibrahima Niang", "niang", "1935199900444", "passer", "niang@gmail.com", true, 770906538L, "Administrateur", new HashSet<Role>()));
            userService.saveUser(new User(2, "Mouhamed Samb", "amed", "1935199900444", "passer", "niang@gmail.com", true, 770906538L, "Administrateur", new HashSet<Role>()));
        };
    }

 */
}
