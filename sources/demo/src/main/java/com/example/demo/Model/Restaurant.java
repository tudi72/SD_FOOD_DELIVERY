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

}
