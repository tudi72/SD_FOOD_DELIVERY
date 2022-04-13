package com.example.demo.Service;

import com.example.demo.Model.Category;
import com.example.demo.Model.DTOs.MealDTO;
import com.example.demo.Model.Meal;
import com.example.demo.Repository.CategoryRepository;
import com.example.demo.Repository.MealRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MealService {
    private final MealRepository mealRepository;
    private final CategoryRepository categoryRepository;
    @Autowired
    MealService(MealRepository mealRepository, CategoryRepository categoryRepository)
    {
        this.categoryRepository = categoryRepository;
        this.mealRepository = mealRepository;
    }

    public List<MealDTO> getMeals(int id){

                return mealRepository
                .getMealsByRestaurantId(id)
                .stream()
                .map(x -> new MealDTO.Builder(x).build())
                .collect(Collectors.toList());

    }

    public Meal registerMeal(String category,MealDTO mealDTO) {
        Category categoryObj = categoryRepository.getById(category);
        Meal meal = MealDTO.toMeal(mealDTO,categoryObj);
        try{
            return mealRepository.saveAndFlush(meal);
        }
        catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    public List<MealDTO> getMealsByCategory(String category) {
        Category category_obj = categoryRepository.getById(category);
        try{
            return mealRepository
                    .getMealsByCategory(category_obj)
                    .stream()
                    .map(x -> new MealDTO.Builder(x).build())
                    .collect(Collectors.toList());

        }
        catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }
}
