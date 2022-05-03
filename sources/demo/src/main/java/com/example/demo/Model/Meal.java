package com.example.demo.Model;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@NamedNativeQuery(name = "Meal.getMealsByRestaurantId",
        query ="select meal.* from meal " +
        "join restaurant_meals " +
        "where restaurant_id = ? and restaurant_meals.meal_id = meal.id", resultClass = Meal.class)
public class Meal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NonNull private String name;
    @NonNull private String ingredients;
    @NonNull private String allergens;
    @NonNull private double weight;
    @NonNull private double price;
    @Column private String imageURL;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

}
