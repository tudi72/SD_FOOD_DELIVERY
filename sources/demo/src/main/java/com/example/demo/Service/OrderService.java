package com.example.demo.Service;

import com.example.demo.Model.MyOrder;
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

    public List<MyOrder> getOrders() {
        try{
            return orderRepository.findAll();
        }
        catch (Exception ex){
            return null;
        }
    }

    public List<MyOrder> getOrdersByStatus(String status) {
        try{
            return orderRepository.findByStatus(status);
        }
        catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }


}
