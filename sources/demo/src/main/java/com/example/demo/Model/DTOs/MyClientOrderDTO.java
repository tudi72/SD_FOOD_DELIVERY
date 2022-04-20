package com.example.demo.Model.DTOs;

import com.example.demo.Model.MyOrder;
import lombok.*;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MyClientOrderDTO {

    int order_id;
    String status;
    String dateAndTime;
    String deliveryTime;

    public MyClientOrderDTO(Builder builder){
        this.status = builder.status;
        this.dateAndTime = builder.dateAndTime;
        this.deliveryTime = builder.deliveryTime;
        this.order_id = builder.order_id;
    }

    public static class Builder{
        private final String status;
        private final String dateAndTime;
        private final String deliveryTime;
        private final int order_id;

        public Builder(MyOrder order){
            this.order_id = order.getId();
            this.status = order.getStatus();
            this.dateAndTime = order.getDateAndTime().toString();
            this.deliveryTime = String.valueOf(order.getDeliveryTime());
        }

        public MyClientOrderDTO build(){
            return new MyClientOrderDTO(this);
        }
    }
}
