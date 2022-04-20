package com.example.demo.Repository;

import com.example.demo.Model.Customer;
import com.example.demo.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {

    Customer findCustomerByUser(User user);
}
