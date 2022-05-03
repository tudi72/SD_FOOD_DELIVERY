package com.example.demo.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;

@Entity
@Data
@Builder
@Getter
@Setter
@ToString
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

}
