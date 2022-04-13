package com.example.demo.Repository;

import com.example.demo.Model.MyOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<MyOrder,Integer> {

    List<MyOrder> findByStatus(String status);
}
