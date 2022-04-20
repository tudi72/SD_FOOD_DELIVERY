package com.example.demo.Service;

import com.example.demo.Model.*;
import com.example.demo.Model.DTOs.MealCopyDTO;
import com.example.demo.Model.DTOs.MealDTO;
import com.example.demo.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MealService {
    private final MealRepository mealRepository;
    private final MealCopyRepository mealCopyRepository;
    private final CategoryRepository categoryRepository;
    private final BasketRepository basketRepository;
    private final RestaurantRepository restaurantRepository;
    @Autowired
    MealService(RestaurantRepository restaurantRepository,BasketRepository basketRepository,MealCopyRepository mealCopyRepository, MealRepository mealRepository, CategoryRepository categoryRepository)
    {
        this.basketRepository = basketRepository;
        this.mealCopyRepository = mealCopyRepository;
        this.categoryRepository = categoryRepository;
        this.mealRepository = mealRepository;
        this.restaurantRepository = restaurantRepository;
    }

    public List<MealDTO> getMeals(int id){

                return mealRepository
                .getMealsByRestaurantId(id)
                .stream()
                .map(x -> new MealDTO.Builder(x).build())
                .collect(Collectors.toList());

    }

    public Meal registerMeal(MealDTO mealDTO,Admin admin) throws ResourceNotFoundException{
        try{
            System.out.print(mealDTO.toString());
            Category categoryObj = categoryRepository.getById(mealDTO.category);
            Meal meal = MealDTO.toMeal(mealDTO,categoryObj);
            if(mealDTO.name.matches("") || mealDTO.price == 0) throw new ResourceNotFoundException("No name or price found");
            final Meal finalMeal =  mealRepository.saveAndFlush(meal);
            Restaurant restaurant = restaurantRepository.findRestaurantByAdmin(admin);
            Set<Meal> meals = restaurant.getMeals();
            meals.add(meal);
            restaurant.setMeals(meals);
            restaurantRepository.save(restaurant);
            return finalMeal;

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

    public MealCopy addMealToBasket(MealCopyDTO mealCopyDTO) {

        Meal meal = mealRepository.findById(mealCopyDTO.meal_id)
                .orElseThrow(() -> new ResourceNotFoundException("Meal not found for id : " + mealCopyDTO.meal_id));

        Basket basket = basketRepository.findById(mealCopyDTO.basket_id)
                .orElseThrow(() -> new ResourceNotFoundException("Meal not found for id : " + mealCopyDTO.basket_id));

        MealCopy mealCopy = mealCopyRepository.findByBasketAndMeal(basket,meal);
        if(mealCopy == null) {
            mealCopy = new MealCopy();
            mealCopy.setQuantity(mealCopyDTO.nr_copies);
            mealCopy.setMeal(meal);
            mealCopy.setBasket(basket);
            return  mealCopyRepository.save(mealCopy);
        }
        else{
            mealCopy.setQuantity(mealCopyDTO.nr_copies + mealCopy.getQuantity());
            return mealCopyRepository.save(mealCopy);
        }

    }
}
