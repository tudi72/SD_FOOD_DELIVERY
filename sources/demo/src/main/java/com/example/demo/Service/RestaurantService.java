package com.example.demo.Service;

import com.example.demo.Model.DTOs.RestaurantDTO;
import com.example.demo.Repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;

    @Autowired
    RestaurantService(RestaurantRepository restaurantRepository){
        this.restaurantRepository = restaurantRepository;
    }


    public List<RestaurantDTO> getAllRestaurants() {
        return restaurantRepository.getAll()
                .stream()
                .map(x -> new RestaurantDTO.Builder(x).build())
                .collect(Collectors.toList());
    }

}
