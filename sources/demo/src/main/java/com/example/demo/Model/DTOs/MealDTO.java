package com.example.demo.Model.DTOs;

import com.example.demo.Model.Meal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MealDTO {
    private String name;
    private String ingredients;
    private String allergens;
    private double weight;
    private double price;
    private String category;

    private MealDTO(Builder builder){
        this.name = builder.name;
        this.ingredients = builder.ingredients;
        this.allergens = builder.allergens;
        this.weight = builder.weight;
        this.price = builder.price;
        this.category = builder.category;
    }

    public static class Builder{

        private final String name;
        private String ingredients;
        private String allergens;
        private double weight;
        private double price;
        private String category;

        public Builder(Meal meal){
            this.name = meal.getName();
            this.ingredients = meal.getIngredients();
            this.allergens = meal.getAllergens();
            this.weight = meal.getWeight();
            this.price = meal.getPrice();
            this.category = meal.getCategory();
        }
        public MealDTO build(){
            return new MealDTO(this);
        }
    }
}
