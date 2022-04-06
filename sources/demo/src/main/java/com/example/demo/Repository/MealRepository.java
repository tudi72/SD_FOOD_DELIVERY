package com.example.demo.Repository;


import com.example.demo.Model.Meal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MealRepository extends JpaRepository<Meal,Integer> {

   List<Meal> getMealsByRestaurantId(Integer restaurant_id);
 }
