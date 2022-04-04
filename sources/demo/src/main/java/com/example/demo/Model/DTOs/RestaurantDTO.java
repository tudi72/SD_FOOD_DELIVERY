package com.example.demo.Model.DTOs;

import com.example.demo.Model.Meal;
import com.example.demo.Model.Neighbourhood;

import java.util.List;
import java.util.Set;

public class RestaurantDTO {

    public String name;

    public String location;

    public List<Meal> meals;

    public Set<Neighbourhood> neighbourhoods;
    
}
