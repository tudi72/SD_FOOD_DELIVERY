package com.example.demo;

import com.example.demo.Model.Authority;
import com.example.demo.Model.User;
import com.example.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.PostConstruct;
import java.sql.Date;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.example.demo.Repository")
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    UserService userService;
    @Autowired


    @PostConstruct
    protected void init(){
        List<Authority> authorities = new ArrayList<>();
        authorities.add(new Authority(null, "ADMIN","restaurant administrattor"));
        authorities.add(new Authority(null, "CUSTOMER","customer of restaurants"));
        authorities.add(new Authority(null, "DEVELOPER","just tudi"));

        authorities.stream().forEach(userService::saveAuthority);

        userService.saveUser(new User(
                0,
                "Tudorita Zaharia",
                "ztudorita@gmail.com",
                passwordEncoder.encode("Shaorma72."),
                Date.from(Instant.now()),
                Date.from(Instant.now()),
                true,
                new ArrayList<>()));
        userService.saveUser(new User(
                0,
                "Arnold Schwarzenegger",
                "arnold@gmail.com",
                passwordEncoder.encode("1234"),
                Date.from(Instant.now()),
                Date.from(Instant.now()),
                true,
                new ArrayList<>()
        ));
        userService.saveUser(new User(
                0,
                "Floricica Dansatoarea",
                "floricica@gmail.com",
                passwordEncoder.encode("1234"),
                Date.from(Instant.now()),
                Date.from(Instant.now()),
                true,
                new ArrayList<>()
        ));

        userService.addRoleToUser("ztudorita@gmail.com", "ADMIN");
        userService.addRoleToUser("arnold@gmail.com", "ADMIN");
        userService.addRoleToUser("floricica@gmail.com", "CUSTOMER");
    }

}
