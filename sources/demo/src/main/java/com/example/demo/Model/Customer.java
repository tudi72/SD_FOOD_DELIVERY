package com.example.demo.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.List;


@Entity
@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String address;

    @Column
    private String phone;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @JsonManagedReference
    @OneToMany(mappedBy = "customer")
    private List<MyOrder> orderList;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<MyOrder> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<MyOrder> orderList) {
        this.orderList = orderList;
    }
}
