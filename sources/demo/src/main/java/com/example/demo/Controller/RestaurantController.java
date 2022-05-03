package com.example.demo.Controller;

import com.example.demo.Model.*;
import com.example.demo.Model.DTOs.MealDTO;
import com.example.demo.Model.DTOs.RestaurantDTO;
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
import java.util.Set;

@RestController
@RequestMapping("/admin")
@CrossOrigin
public class RestaurantController {

    private final RestaurantService restaurantService;
    private final MealService mealService;
    private final OrderService orderService;
    private final UserService userService;
    private ResponseEntity<Admin> admin;
    @Autowired
    RestaurantController(UserService userService,OrderService orderService, MealService mealService, RestaurantService restaurantService){
        this.orderService = orderService;
        this.restaurantService = restaurantService;
        this.mealService = mealService;
        this.userService = userService;
    }



    @GetMapping(value = "/get_neighbourhoods")
    public List<Neighbourhood> getNeighbourhoods(){
        return restaurantService.getAllNeighbourhoods();
    }

    @GetMapping(value = "/get_categories")
    public List<String> getCategories(){
        return restaurantService.getAllCategories();
    }

    @GetMapping(value ="/get_meals")
    public List<MealDTO> getMeals(){
        Admin admin = this.admin == null ? null: this.admin.getBody();
        return mealService.getMeals(admin);
    }

    @PostMapping(value = "/new_restaurant",consumes={"application/json"})
    public Restaurant registerRestaurant(@RequestBody RestaurantDTO restaurant) throws ResourceNotFoundException{
        restaurant.admin_id = this.admin == null ? 0: this.admin.getBody().getId();
        return restaurantService.registerRestaurant(restaurant);
    }


    @PostMapping(value = "/add_neighbourhoods",consumes = {"application/json"})
    public HttpStatus addNeighbourhoods(@RequestBody Set<Neighbourhood> neighbourhoodSet){
        Admin myAdmin = this.admin == null ? null : this.admin.getBody();
        neighbourhoodSet.stream().forEach(System.out::println);
        return restaurantService.addNeighbourhoods(myAdmin,neighbourhoodSet);
    }

    @PostMapping(value ="/new_meal",consumes ={"application/json"})
    @ResponseStatus(code = HttpStatus.CREATED)
    public Meal registerMeal(@RequestBody MealDTO mealDTO)throws ResourceNotFoundException{
        Admin myAdmin = this.admin == null ? null : this.admin.getBody();
        return mealService.registerMeal(mealDTO,myAdmin);
    }

    @GetMapping(value = "/view_category_menu/{category_id}")
    public List<MealDTO> viewMealsByCategory(@PathVariable("category_id") String category){
        return mealService.getMealsByCategory(category);
    }

    @GetMapping(value = "/view_orders")
    public List<MyOrder> viewOrders(){
        Admin myAdmin = this.admin == null ? null : this.admin.getBody();
        return orderService.getOrders(myAdmin);
    }

    @GetMapping(value = "/view_orders/{status}",consumes= {"application/json"})
    public List<MyOrder> viewOrderByStatus(@PathVariable("status")String status){
        return orderService.getOrdersByStatus(status);
    }

    @PostMapping(value = "view_orders/accept/{order_id}")
    public ResponseEntity<MyOrder> acceptOrder(@PathVariable("order_id")int order_id)
    throws ResourceNotFoundException {
        return orderService.acceptOrder(order_id);
    }

    @PostMapping(value = "view_orders/decline/{order_id}")
    public ResponseEntity<MyOrder> declineOrder(@PathVariable("order_id")int order_id) throws
            ResourceNotFoundException{
        return orderService.declineOrder(order_id);
    }

    @PostMapping(value = "view_orders/change_status/{order_id}/{new_status}")
    public ResponseEntity<MyOrder> updateOrderStatus(@PathVariable("order_id")int order_id,
                                                     @PathVariable("new_status")String status) throws ResourceNotFoundException{
        System.out.print(order_id);
        System.out.print(status);
        return orderService.updateOrderStatus(order_id,status);
    }

    @PostMapping(value = "get_PDF")
    public HttpStatus exportMenuAsPDF(){
        Admin admin = this.admin == null ? null: this.admin.getBody();
        return restaurantService.exportMenuAsPDF(admin);
    }
}
