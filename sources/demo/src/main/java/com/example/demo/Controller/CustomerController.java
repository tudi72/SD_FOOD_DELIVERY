package com.example.demo.Controller;


import com.example.demo.Model.Restaurant;
import com.example.demo.Model.User;
import com.example.demo.Service.RestaurantService;
import com.example.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
@CrossOrigin
public class CustomerController {

    private final UserService userService;
    private final RestaurantService restaurantService;

    @Autowired
    public CustomerController(UserService userService, RestaurantService restaurantService){
        this.userService = userService;
        this.restaurantService = restaurantService;
    }

    @PostMapping(value = "/register",consumes ={"application/json"})
    @ResponseStatus(code = HttpStatus.CREATED)
    public User registerUser(@RequestBody User user){
        return  userService.addUser(user);
    }


    @GetMapping(value = "/findall")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping(value = "/restaurants")
    public List<Restaurant> getRestaurants(){
        return restaurantService.getAllRestaurants();
    }
}
