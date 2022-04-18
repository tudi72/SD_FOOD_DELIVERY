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

        public Builder(MyOrder order){
            this.deliveryTime = String.valueOf(order.getDeliveryTime());
            this.basket_id = order.getBasket().getId();
            this.customer_id = order.getCustomer().getId();
        }

        public MyOrderDTO build() { return new MyOrderDTO(this);}
    }

}
