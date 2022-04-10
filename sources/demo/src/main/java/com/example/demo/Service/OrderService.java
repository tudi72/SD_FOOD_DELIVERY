package com.example.demo.Service;

import com.example.demo.Model.Order;
import com.example.demo.Repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    OrderService(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }


    public List<Order> getOrders() {
        try{
            return orderRepository.findAll();
        }
        catch (Exception ex){
            return null;
        }
    }
}
