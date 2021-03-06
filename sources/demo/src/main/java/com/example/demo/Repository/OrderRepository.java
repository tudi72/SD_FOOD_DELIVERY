package com.example.demo.Repository;

import com.example.demo.Model.Basket;
import com.example.demo.Model.Customer;
import com.example.demo.Model.MyOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<MyOrder,Integer> {

    List<MyOrder> findByStatus(String status);

    List<MyOrder> findByCustomer(Customer customer);

    MyOrder findTopByCustomer(int customer_id);

    List<MyOrder> findMyOrdersByBasket(Basket basket);
}
