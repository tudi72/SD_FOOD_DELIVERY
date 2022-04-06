package com.example.demo.Model.DTOs;

import com.example.demo.Model.Restaurant;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantDTO {

    public String name;

    public String location;

    private RestaurantDTO(Builder builder) {
        this.name = builder.name;
        this.location = builder.location;
    }

    public static class Builder{

        private final String name;
        private final String location;

        public Builder(Restaurant restaurant){
            this.name = restaurant.getName();
            this.location = restaurant.getLocation();
        }
        public RestaurantDTO build(){
            return new RestaurantDTO(this);
        }
    }
}
