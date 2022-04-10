package com.example.demo.Service;

import com.example.demo.Model.Admin;
import com.example.demo.Model.DTOs.RestaurantDTO;
import com.example.demo.Model.Restaurant;
import com.example.demo.Repository.AdminRepository;
import com.example.demo.Repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;
    private final AdminRepository adminRepository;

    @Autowired
    RestaurantService(AdminRepository adminRepository,RestaurantRepository restaurantRepository){
        this.restaurantRepository = restaurantRepository;
        this.adminRepository = adminRepository;
    }


    public List<RestaurantDTO> getAllRestaurants() {
        return restaurantRepository.getAll()
                .stream()
                .map(x -> new RestaurantDTO.Builder(x).build())
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
}
