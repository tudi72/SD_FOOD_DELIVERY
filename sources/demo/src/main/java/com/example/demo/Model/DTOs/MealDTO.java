package com.example.demo.Model.DTOs;

import com.example.demo.Model.Category;
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
    public String name;
    public String ingredients;
    public String allergens;
    public double weight;
    public double price;
    public String category;
    public String imageURL;

    private MealDTO(Builder builder){
        this.name = builder.name;
        this.ingredients = builder.ingredients;
        this.allergens = builder.allergens;
        this.weight = builder.weight;
        this.price = builder.price;
        this.category = builder.category;
        this.imageURL = builder.imageURL;
    }

    public static Meal toMeal(MealDTO mealDTO, Category category) {
        Meal meal = new Meal();
        meal.setAllergens(mealDTO.allergens);
        meal.setIngredients(mealDTO.ingredients);
        meal.setName(mealDTO.name);
        meal.setPrice(mealDTO.price);
        meal.setWeight(mealDTO.weight);
        meal.setCategory(category);
        return meal;
    }

    public static class Builder{

        private final String name;
        private final String imageURL;
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
            this.imageURL = meal.getImageURL();
        }
        public MealDTO build(){
            return new MealDTO(this);
        }
    }
}
