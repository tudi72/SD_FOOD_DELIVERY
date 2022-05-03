package com.example.demo;

import com.example.demo.Model.Authority;
import com.example.demo.Model.User;
import com.example.demo.Service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.sql.Date;
import java.time.Instant;
import java.util.ArrayList;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.example.demo.Repository")
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    CommandLineRunner run(UserService userService) {
        return args -> {
            userService.saveAuthority(new Authority(null, "ADMIN","restaurant administrattor"));
            userService.saveAuthority(new Authority(null, "CUSTOMER","customer of restaurants"));
            userService.saveAuthority(new Authority(null, "DEVELOPER","just tudi"));

            userService.saveUser(new User(
                    0,
                    "Tudorita Zaharia",
                    "ztudorita@gmail.com",
                    "Shaorma72.",
                    Date.from(Instant.now()),
                    Date.from(Instant.now()),
                    true,
                    new ArrayList<>()));
            userService.saveUser(new User(
                    0,
                    "Arnold Schwarzenegger",
                    "arnold@gmail.com",
                    "1234",
                    Date.from(Instant.now()),
                    Date.from(Instant.now()),
                    true,
                    new ArrayList<>()
            ));
            userService.saveUser(new User(
                    0,
                    "Floricica Dansatoarea",
                    "floricica@gmail.com",
                    "1234",
                    Date.from(Instant.now()),
                    Date.from(Instant.now()),
                    true,
                    new ArrayList<>()
            ));
            userService.addRoleToUser("ztudorita@gmail.com", "ADMIN");
            userService.addRoleToUser("arnold@gmail.com", "ADMIN");
            userService.addRoleToUser("floricica@gmail.com", "CUSTOMER");

        };
    }
}
