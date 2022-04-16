package com.example.demo.Controller;


import com.example.demo.Model.Basket;
import com.example.demo.Model.DTOs.MealCopyDTO;
import com.example.demo.Model.DTOs.MealDTO;
import com.example.demo.Model.DTOs.MyOrderDTO;
import com.example.demo.Model.DTOs.RestaurantDTO;
import com.example.demo.Model.MealCopy;
import com.example.demo.Model.MyOrder;
import com.example.demo.Model.User;
import com.example.demo.Service.MealService;
import com.example.demo.Service.RestaurantService;
import com.example.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
@CrossOrigin
public class CustomerController {

    private final UserService userService;
    private final RestaurantService restaurantService;
    private final MealService mealService;

    @Autowired
    public CustomerController(MealService mealService, UserService userService, RestaurantService restaurantService){
        this.userService = userService;
        this.restaurantService = restaurantService;
        this.mealService = mealService;
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
    public List<RestaurantDTO> getRestaurants(){
        return restaurantService.getAllRestaurants();
    }

    @GetMapping(value = "/restaurants/{restaurant_id}", consumes = {"application/json"})
    public List<MealDTO> getRestaurantMenu(@PathVariable("restaurant_id") int id){
        return mealService.getMeals(id);
    }

    @PostMapping(value = "/restaurants/{restaurant_id}/create_basket",consumes = {"application/json"})
    public Basket registerBasket(@PathVariable("restaurant_id")int restaurant_id)
    throws ResourceNotFoundException {
      return  restaurantService.registerBasket(restaurant_id);
    }

    @PostMapping(value = "/restaurants/add_meal",consumes = {"application/json"})
    public MealCopy addMealToBasket(@RequestBody MealCopyDTO mealCopyDTO)
    {
        return mealService.addMealToBasket(mealCopyDTO);
    }

    //TODO implement placing an order based on a list of meals and a basket for a specific restaurant
    @PostMapping(value = "restaurants/place_order",consumes = {"application/json"})
    public MyOrder registerOrder(@RequestBody MyOrderDTO myOrderDTO){
        return null;
    }
}
