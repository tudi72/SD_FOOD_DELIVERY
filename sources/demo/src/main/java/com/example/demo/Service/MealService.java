package com.example.demo.Service;

import com.example.demo.Model.DTOs.MealDTO;
import com.example.demo.Repository.MealRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MealService {
    private final MealRepository mealRepository;

    @Autowired
    MealService(MealRepository mealRepository){
        this.mealRepository = mealRepository;
    }

    // TODO : get all meals based on restaurant id
    public List<MealDTO> getMeals(int id){

                return mealRepository
                .getMealsByRestaurantId(id)
                .stream()
                .map(x -> new MealDTO.Builder(x).build())
                .collect(Collectors.toList());

    }
}
