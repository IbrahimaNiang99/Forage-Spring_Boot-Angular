package com.senforage.backforage;

import com.senforage.backforage.Entity.Role;
import com.senforage.backforage.Entity.User;
import com.senforage.backforage.Services.Userservice.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class BackforageApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackforageApplication.class, args);
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    /*
    @Bean
    CommandLineRunner run(UserService userService){
        return args -> {
            userService.addRole(new Role(null, "ROLE_ADMIN"));
            userService.addRole(new Role(null, "ROLE_GESCLIENTELE"));
            userService.addRole(new Role(null, "ROLE_GESCOMMERCIALE"));
            userService.addRole(new Role(null, "ROLE_GESCOMPTEUR"));

            userService.addUser(new User(null, "Ibrahima Niang", "niang", "niang031999@gmail.com","passer", new ArrayList<>()));
            userService.addUser(new User(null, "Mouhamed Fall", "fall", "fall@gmail.com","passer", new ArrayList<>()));
            userService.addUser(new User(null, "Ndéye Awa Bâ", "awa", "awa@gmail.com","passer", new ArrayList<>()));
            userService.addUser(new User(null, "Balla Diéne", "balla", "balla@gmail.com","passer", new ArrayList<>()));

            userService.addRoleToUser("niang", "ROLE_ADMIN");
            userService.addRoleToUser("fall", "ROLE_GESCLIENTELE");
            userService.addRoleToUser("awa", "ROLE_GESCOMMERCIALE");
            userService.addRoleToUser("balla", "ROLE_GESCOMPTEUR");
        };
    }*/

}
