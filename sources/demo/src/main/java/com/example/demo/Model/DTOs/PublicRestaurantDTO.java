package com.example.demo.Model.DTOs;

import com.example.demo.Model.Admin;
import com.example.demo.Model.Restaurant;
import lombok.*;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PublicRestaurantDTO {

    public int restaurant_id;
    public String title;
    public String location;

    private PublicRestaurantDTO(Builder builder){
        this.restaurant_id = builder.restaurant_id;
        this.title = builder.title;
        this.location = builder.location;
    }

    public static class Builder{
        private final String title;
        private final String location;
        private final int restaurant_id;
        public Builder(Restaurant restaurant){
            this.restaurant_id = restaurant.getId();;
            this.title = restaurant.getName();
            this.location = restaurant.getLocation();
        }
        public PublicRestaurantDTO build() {return new PublicRestaurantDTO(this);}
    }

}
