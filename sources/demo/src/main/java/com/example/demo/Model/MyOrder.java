package com.example.demo.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.springframework.boot.context.properties.bind.Name;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@NamedNativeQuery(name = "MyOrder.findTopByCustomer",query = "SELECT * FROM food_delivery.my_order where customer_id = ? order by date_and_time desc LIMIT 1;",resultClass = MyOrder.class)
public class MyOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column
    private String status;

    @Column
    private Timestamp dateAndTime;

    @Column
    private Timestamp deliveryTime;

    @JsonBackReference
    @OneToOne
    @JoinColumn(name = "basket_id")
    private Basket basket;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER,optional = false)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public void setStatus(String status) {
        this.status = status;
    }

    public void setDateAndTime(Timestamp dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public void setDeliveryTime(Timestamp deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public void setBasket(Basket basket) {
        this.basket = basket;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getStatus() {
        return status;
    }

    public Timestamp getDateAndTime() {
        return dateAndTime;
    }

    public Timestamp getDeliveryTime() {
        return deliveryTime;
    }

    public Basket getBasket() {
        return basket;
    }

    public Customer getCustomer() {
        return customer;
    }

    public int getId() {
        return id;
    }
}
