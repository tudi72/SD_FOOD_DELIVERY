package com.example.demo.Repository;

import com.example.demo.Model.Basket;
import com.example.demo.Model.Meal;
import com.example.demo.Model.MealCopy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MealCopyRepository extends JpaRepository<MealCopy,Integer> {


    MealCopy findByBasketAndMeal(Basket basket, Meal meal);


}
