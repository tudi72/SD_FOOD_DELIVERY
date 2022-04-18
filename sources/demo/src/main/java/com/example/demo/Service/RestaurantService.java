package com.example.demo.Service;

import com.example.demo.Model.Admin;
import com.example.demo.Model.Basket;
import com.example.demo.Model.DTOs.PublicRestaurantDTO;
import com.example.demo.Model.DTOs.RestaurantDTO;
import com.example.demo.Model.Restaurant;
import com.example.demo.Repository.AdminRepository;
import com.example.demo.Repository.BasketRepository;
import com.example.demo.Repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;
    private final AdminRepository adminRepository;
    private final BasketRepository basketRepository;

    @Autowired
    RestaurantService(BasketRepository basketRepository,AdminRepository adminRepository,RestaurantRepository restaurantRepository){
        this.restaurantRepository = restaurantRepository;
        this.adminRepository = adminRepository;
        this.basketRepository = basketRepository;
    }


    public List<PublicRestaurantDTO> getAllRestaurants() {
        return restaurantRepository.getAll()
                .stream()
                .map(x -> new PublicRestaurantDTO.Builder(x).build())
                .collect(Collectors.toList());
    }

    public Restaurant registerRestaurant(RestaurantDTO restaurantDTO) {

        Admin admin = adminRepository.findById(restaurantDTO.admin_id).orElse(null);
        if(admin == null) return null;
        Restaurant restaurant = RestaurantDTO.toRestaurant(restaurantDTO,admin);
        try {
            return restaurantRepository.saveAndFlush(restaurant);
        }
        catch (Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage());
            return null;
        }
    }

    public Basket registerBasket(int restaurant_id) throws ResourceNotFoundException{
        Restaurant restaurant = restaurantRepository.findById(restaurant_id)
                .orElseThrow(() -> new ResourceNotFoundException("Restaurant not found for id " + restaurant_id));
        Basket basket = new Basket();
        basket.setRestaurant(restaurant);

        return basketRepository.save(basket);
    }

    public List<RestaurantDTO> getAllRestaurantsByName(String name) {
        return restaurantRepository.getAllByName(name)
                .stream()
                .map(x -> new RestaurantDTO.Builder(x).build())
                .collect(Collectors.toList());
    }
}
