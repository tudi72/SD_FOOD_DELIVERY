package com.example.demo.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MealCopy {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "meal_id")
    private Meal meal;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "basket_id")
    private Basket basket;

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setMeal(Meal meal) {
        this.meal = meal;
    }

    public void setBasket(Basket basket) {
        this.basket = basket;
    }
}
