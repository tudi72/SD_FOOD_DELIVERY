package com.example.demo.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
    @ManyToOne(fetch = FetchType.EAGER,optional = false)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public void setStatus(String status) {
        this.status = status;
    }
}
