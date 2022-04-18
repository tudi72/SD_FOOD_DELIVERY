package com.example.demo.Service;

import com.example.demo.Model.Basket;
import com.example.demo.Model.Customer;
import com.example.demo.Model.DTOs.MyClientOrderDTO;
import com.example.demo.Model.DTOs.MyOrderDTO;
import com.example.demo.Model.MyOrder;
import com.example.demo.Repository.BasketRepository;
import com.example.demo.Repository.CustomerRepository;
import com.example.demo.Repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final BasketRepository basketRepository;

    @Autowired
    OrderService(CustomerRepository customerRepository,BasketRepository basketRepository,OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.basketRepository = basketRepository;
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

    public MyOrder registerOrder(MyOrderDTO myOrderDTO) throws ResourceNotFoundException{
        MyOrder order = new MyOrder();
        order.setStatus("PENDING");
        order.setDateAndTime(Timestamp.from(Instant.now()));
        order.setDeliveryTime(Timestamp.valueOf(
                new SimpleDateFormat("yyyy-MM-dd ")
                .format(new Date())
                .concat(myOrderDTO.deliveryTime)
        ));
        Basket basket = basketRepository.findById(myOrderDTO.basket_id)
                .orElseThrow(() -> new ResourceNotFoundException("Basket not found for id " + myOrderDTO.basket_id));

        Customer customer = customerRepository.findById(myOrderDTO.customer_id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found for id " + myOrderDTO.customer_id));
        order.setBasket(basket);
        order.setCustomer(customer);

        return orderRepository.save(order);
    }

    public MyClientOrderDTO getLastOrder(int customer_id) throws ResourceNotFoundException{
        try {
            MyOrder order = orderRepository.findTopByCustomer(customer_id);
            MyClientOrderDTO myClientOrderDTO = new MyClientOrderDTO.Builder(order).build();
            System.out.print(myClientOrderDTO.toString());
            return myClientOrderDTO;
        }
        catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    public List<MyClientOrderDTO> getClientOrders(int customer_id) {
        Customer customer = customerRepository.findById(customer_id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found for id" + customer_id));
        return orderRepository
                .findByCustomer(customer)
                .stream()
                .map(x -> new MyClientOrderDTO.Builder(x).build())
                .collect(Collectors.toList());
    }
}