package com.example.demo.Service;

import com.example.demo.Model.Admin;
import com.example.demo.Model.Customer;
import com.example.demo.Model.User;
import com.example.demo.Repository.AdminRepository;
import com.example.demo.Repository.CustomerRepository;
import com.example.demo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final CustomerRepository customerRepository;
    private final AdminRepository adminRepository;

    @Autowired
    public UserService(CustomerRepository customerRepository,AdminRepository adminRepository,UserRepository repository){
        this.userRepository = repository;
        this.customerRepository = customerRepository;
        this.adminRepository = adminRepository;
    }

    public ResponseEntity<Customer> addUser(User user) {
        User alreadyUser = userRepository.findByEmail(user.getEmail());
        if(alreadyUser == null){
            if(user.getEmail().equals("") || user.getPassword().equals("") || user.getPassword().length() < 5)
                return null;
            else if (user.getEmail().matches("^(.+)@(.+)$")){
                User newUser = userRepository.saveAndFlush(user);
                Customer customer = new Customer();
                customer.setUser(newUser);
                final Customer finalCustomer =  customerRepository.saveAndFlush(customer);
                return ResponseEntity.ok(finalCustomer);
            }
            else return null;
        }
        else return null;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


    public ResponseEntity<Customer> loginUser(User user) {

        User isUser = userRepository.findByEmail(user.getEmail());
        if(isUser != null){
            Customer isCustomer = customerRepository.findCustomerByUser(isUser);
            return ResponseEntity.ok(isCustomer);
        }
        else return null;
    }

    public ResponseEntity<Admin> loginAdmin(User user) {
        User isUser = userRepository.findByEmail(user.getEmail());
        if(isUser != null){
            Admin isAdmin = adminRepository.findAdminByUser(isUser);
            return ResponseEntity.ok(isAdmin);
        }
        else return null;
    }
}
