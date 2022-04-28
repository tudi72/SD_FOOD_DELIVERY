package com.example.demo.Repository;

import com.example.demo.Model.Basket;
import com.example.demo.Model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BasketRepository extends JpaRepository<Basket,Integer> {

    List<Basket> findBasketsByRestaurant(Restaurant restaurant);
}
