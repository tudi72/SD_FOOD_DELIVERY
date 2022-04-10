package com.example.demo.Model.DTOs;

import com.example.demo.Model.Admin;
import com.example.demo.Model.Restaurant;
import lombok.*;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantDTO {
    public int id;

    public String name;

    public String location;

    public int admin_id;

    private RestaurantDTO(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.location = builder.location;
        this.admin_id = builder.admin_id;
    }

    public static class Builder{
        private final int id;
        private final String name;
        private final String location;
        private final int admin_id;

        public Builder(Restaurant restaurant){
            this.id = restaurant.getId();
            this.name = restaurant.getName();
            this.location = restaurant.getLocation();
            this.admin_id = restaurant.getAdminId();
        }
        public RestaurantDTO build(){
            return new RestaurantDTO(this);
        }
    }

    public static Restaurant toRestaurant(RestaurantDTO restaurantDTO, Admin admin){
        Restaurant restaurant = new Restaurant();
        restaurant.setAdmin(admin);
        restaurant.setLocation(restaurantDTO.location);
        restaurant.setName(restaurantDTO.name);
        restaurant.setMeals(null);
        restaurant.setNeighbourhoodList(null);
        return restaurant;
    }
}
