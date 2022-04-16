package com.example.demo.Repository;

import com.example.demo.Model.Meal;
import com.example.demo.Model.MealCopy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MealCopyRepository extends JpaRepository<MealCopy,Integer> {

    MealCopy findByQuantityAndMeal(int quantity, Meal meal);
}
