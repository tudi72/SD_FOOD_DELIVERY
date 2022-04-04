package com.example.demo.Service;

import com.example.demo.Model.DTOs.RestaurantDTO;
import com.example.demo.Model.Restaurant;
import com.example.demo.Repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;

    @Autowired
    RestaurantService(RestaurantRepository restaurantRepository){
        this.restaurantRepository = restaurantRepository;
    }


    public List<Restaurant> getAllRestaurants() {
        List<Restaurant> list =  restaurantRepository.getAll();
        return list;
    }

}
