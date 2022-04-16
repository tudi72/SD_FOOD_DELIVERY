package com.example.demo.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column
    private String name;

    @Column
    private String location;

    @JsonBackReference
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "admin_id")
    private Admin admin;

    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinTable( name                =   "restaurant_neighbourhoods",
                joinColumns         =   {@JoinColumn(name = "restaurant_id")},
                inverseJoinColumns  =   {@JoinColumn(name = "neighbourhood_id")})
    Set<Neighbourhood> neighbourhoodList;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable( name = "restaurant_meals",
                joinColumns = {@JoinColumn(name = "restaurant_id")},
                inverseJoinColumns = {@JoinColumn(name = "meal_id")}
    )
    Set<Meal> meals;


    @JsonManagedReference
    @OneToMany(mappedBy = "restaurant",fetch = FetchType.LAZY)
    private List<Basket> basketList;

    public int getId(){ return id;}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public int getAdminId() {
        return admin.getId();
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public void setNeighbourhoodList(Set<Neighbourhood> neighbourhoodList) {
        this.neighbourhoodList = neighbourhoodList;
    }

    public void setMeals(Set<Meal> meals) {
        this.meals = meals;
    }
}
