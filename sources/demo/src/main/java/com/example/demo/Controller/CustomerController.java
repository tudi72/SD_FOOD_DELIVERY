package com.example.demo.Controller;


import com.example.demo.Model.*;
import com.example.demo.Model.DTOs.*;
import com.example.demo.Service.MealService;
import com.example.demo.Service.OrderService;
import com.example.demo.Service.RestaurantService;
import com.example.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
@CrossOrigin
public class CustomerController {

    private final UserService userService;
    private final RestaurantService restaurantService;
    private final MealService mealService;
    private final OrderService orderService;

    @Autowired
    public CustomerController(OrderService orderService,MealService mealService, UserService userService, RestaurantService restaurantService){
        this.userService = userService;
        this.restaurantService = restaurantService;
        this.mealService = mealService;
        this.orderService = orderService;
    }

    @PostMapping(value = "/register",consumes ={"application/json"})
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<Customer> registerUser(@RequestBody User user){
        return  userService.addUser(user);
    }


    @GetMapping(value = "/findall")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }


    @GetMapping(value = "/restaurants")
    public List<PublicRestaurantDTO> getRestaurants(){
        return restaurantService.getAllRestaurants();
    }

    @GetMapping(value = "/restaurants/find_by_name/{restaurant_name}")
    public List<RestaurantDTO> getRestaurantsByName(@PathVariable("restaurant_name")String name) throws ResourceNotFoundException{
       return restaurantService.getAllRestaurantsByName(name);
    }

    @GetMapping(value = "/restaurants/{restaurant_id}")
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

    @PostMapping(value = "/restaurants/place_order",consumes = {"application/json"})
    public MyOrder registerOrder(@RequestBody MyOrderDTO myOrderDTO) throws ResourceNotFoundException{
        return orderService.registerOrder(myOrderDTO);
    }

    @GetMapping(value ="/my_orders/{client_id}/view_status")
    public MyClientOrderDTO getLastOrder(@PathVariable("client_id")int client_id) throws ResourceNotFoundException{
        return orderService.getLastOrder(client_id);
    }

    @GetMapping(value = "/my_orders/{client_id}/view_all")
    public List<MyClientOrderDTO> getClientOrders(@PathVariable("client_id") int client_id) throws ResourceNotFoundException{
        return orderService.getClientOrders(client_id);
    }

}
