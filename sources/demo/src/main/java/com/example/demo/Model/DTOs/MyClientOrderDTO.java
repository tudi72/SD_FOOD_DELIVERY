package com.example.demo.Model.DTOs;

import com.example.demo.Model.MyOrder;
import lombok.*;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MyClientOrderDTO {

    String status;
    String dateAndTime;
    String deliveryTime;

    public MyClientOrderDTO(Builder builder){
        this.status = builder.status;
        this.dateAndTime = builder.dateAndTime;
        this.deliveryTime = builder.deliveryTime;
    }

    public static class Builder{
        private final String status;
        private final String dateAndTime;
        private final String deliveryTime;

        public Builder(MyOrder order){
            this.status = order.getStatus();
            this.dateAndTime = order.getDateAndTime().toString();
            this.deliveryTime = String.valueOf(order.getDeliveryTime());
        }

        public MyClientOrderDTO build(){
            return new MyClientOrderDTO(this);
        }
    }
}
