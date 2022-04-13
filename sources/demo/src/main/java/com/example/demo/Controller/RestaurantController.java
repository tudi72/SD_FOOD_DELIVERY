package com.example.demo.Controller;

import com.example.demo.Model.DTOs.MealDTO;
import com.example.demo.Model.DTOs.RestaurantDTO;
import com.example.demo.Model.Meal;
import com.example.demo.Model.MyOrder;
import com.example.demo.Model.Restaurant;
import com.example.demo.Service.MealService;
import com.example.demo.Service.OrderService;
import com.example.demo.Service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@CrossOrigin
public class RestaurantController {

    private final RestaurantService restaurantService;
    private final MealService mealService;
    private final OrderService orderService;

    @Autowired
    RestaurantController(OrderService orderService, MealService mealService, RestaurantService restaurantService){
        this.orderService = orderService;
        this.restaurantService = restaurantService;
        this.mealService = mealService;
    }

    @PostMapping(value = "/new_restaurant",consumes={"application/json"})
    @ResponseStatus(code = HttpStatus.CREATED)
    public Restaurant registerRestaurant(@RequestBody RestaurantDTO restaurant){
        return restaurantService.registerRestaurant(restaurant);
    }

    @PostMapping(value ="/new_meal/{category}",consumes ={"application/json"})
    @ResponseStatus(code = HttpStatus.CREATED)
    public Meal registerMeal(@PathVariable("category")String category,@RequestBody MealDTO mealDTO){
        return mealService.registerMeal(category,mealDTO);
    }

    @GetMapping(value = "/view_category_menu/{category_id}",consumes = {"application/json"})
    public List<MealDTO> viewMealsByCategory(@PathVariable("category_id") String category){
        return mealService.getMealsByCategory(category);
    }

    @GetMapping(value = "/view_orders")
    public List<MyOrder> viewOrders(){
        return orderService.getOrders();
    }

    @GetMapping(value = "/view_orders/{status}",consumes= {"application/json"})
    public List<MyOrder> viewOrderByStatus(@PathVariable("status")String status){
        return orderService.getOrdersByStatus(status);
    }

    @PutMapping(value = "view_orders/accept/{order_id}",consumes = {"application/json"})
    public ResponseEntity<MyOrder> acceptOrder(@PathVariable("order_id")int order_id)
    throws ResourceNotFoundException {
        return orderService.acceptOrder(order_id);
    }

    @PutMapping(value = "view_orders/decline/{order_id}",consumes = {"application/json"})
    public ResponseEntity<MyOrder> declineOrder(@PathVariable("order_id")int order_id) throws
            ResourceNotFoundException{
        return orderService.declineOrder(order_id);
    }

    @PutMapping(value = "view_orders/change_status/{order_id}/{new_status}",consumes = {"application/json"})
    public ResponseEntity<MyOrder> updateOrderStatus(@PathVariable("order_id")int order_id,
                                                     @PathVariable("new_status")String status) throws ResourceNotFoundException{
        return orderService.updateOrderStatus(order_id,status);
    }

}
