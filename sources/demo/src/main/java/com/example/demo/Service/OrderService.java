package com.example.demo.Service;

import com.example.demo.Model.MyOrder;
import com.example.demo.Repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<MyOrder> getOrders() {
        try {
            return orderRepository.findAll();
        } catch (Exception ex) {
            return null;
        }
    }

    public List<MyOrder> getOrdersByStatus(String status) {
        try {
            return orderRepository.findByStatus(status);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }


    public ResponseEntity<MyOrder> acceptOrder(int order_id) throws ResourceNotFoundException {
        MyOrder order = orderRepository.findById(order_id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found for id : " + order_id));

        order.setStatus("ACCEPTED");
        final MyOrder updatedOrder = orderRepository.save(order);
        return ResponseEntity.ok(updatedOrder);
    }

    public ResponseEntity<MyOrder> declineOrder(int order_id) {
        MyOrder order = orderRepository.findById(order_id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found for id : " + order_id));

        order.setStatus("DECLINED");
        final MyOrder updatedOrder = orderRepository.save(order);
        return ResponseEntity.ok(updatedOrder);
    }

    public ResponseEntity<MyOrder> updateOrderStatus(int order_id, String status) {
        MyOrder order = orderRepository.findById(order_id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found for id : " + order_id));

        order.setStatus(status);
        final MyOrder updatedOrder = orderRepository.save(order);
        return ResponseEntity.ok(updatedOrder);
    }
}