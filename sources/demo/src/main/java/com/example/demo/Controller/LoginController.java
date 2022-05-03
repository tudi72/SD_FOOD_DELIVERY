package com.example.demo.Controller;

import com.example.demo.Model.Admin;
import com.example.demo.Model.Customer;
import com.example.demo.Model.User;
import com.example.demo.Service.AdminService;
import com.example.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
@CrossOrigin
public class LoginController {
    private final UserService userService;

    @Autowired
    public LoginController(UserService userService){
        this.userService = userService;
    }

    @PostMapping(value = "/register",consumes ={"application/json"})
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<User> registerUser(@RequestBody User user){
        return ResponseEntity.ok().body(userService.saveUser(user));
    }

//    @PostMapping(value = "/login",consumes = {"application/json"})
//    public ResponseEntity<User> loginUser(@RequestBody User user){
//        return userService.
//    }

    @GetMapping(value = "/findall")
    public List<User> getAllUsers(){
        return userService.getUsers();
    }



}
