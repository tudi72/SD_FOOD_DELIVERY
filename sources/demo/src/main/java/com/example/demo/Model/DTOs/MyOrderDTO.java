package com.example.demo.Model.DTOs;


import com.example.demo.Model.MyOrder;
import lombok.*;

import java.sql.Time;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MyOrderDTO {

    public String address;

    public String phone;

    public String deliveryTime;

    public int basket_id;

    public int customer_id;

    public MyOrderDTO(Builder builder){
        this.deliveryTime = builder.deliveryTime;
        this.basket_id = builder.basket_id;
        this.customer_id = builder.customer_id;
    }

    public static class Builder{
        private final String deliveryTime;
        private final int basket_id;
        private final int customer_id;
        private String address;
        private String  phone;

        public Builder(MyOrder order){
            this.deliveryTime = String.valueOf(order.getDeliveryTime());
            this.basket_id = order.getBasket().getId();
            this.customer_id = order.getCustomer().getId();
        }

        public Builder phone(String phone){
            this.phone = phone;
            return this;
        }

        public Builder address(String address){
            this.address = address;
            return this;
        }

        public MyOrderDTO build() { return new MyOrderDTO(this);}
    }

}
