package com.example.demo.Service;

import com.example.demo.Model.Customer;
import com.example.demo.Model.User;
import com.example.demo.Repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private CustomerRepository customerRepository;



}
