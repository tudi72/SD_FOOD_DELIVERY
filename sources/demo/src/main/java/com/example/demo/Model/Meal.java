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

    @Column
    private String name;

    @Column
    private String ingredients;

    @Column
    private String allergens;

    @Column
    private double weight;

    @Column
    private double price;

    @Column
    private String imageURL;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getAllergens() {
        return allergens;
    }

    public void setAllergens(String allergens) {
        this.allergens = allergens;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCategory() {
        return category.getCategory();
    }

   public void setCategory(Category category){
        this.category = (category);
   }

    public String getImageURL() {
        return imageURL;
    }
}
