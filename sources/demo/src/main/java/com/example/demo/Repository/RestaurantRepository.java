package com.example.demo.Repository;

import com.example.demo.Model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant,Integer> {

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = "SELECT * from restaurant", nativeQuery = true)
    public List<Restaurant> getAll();
}
