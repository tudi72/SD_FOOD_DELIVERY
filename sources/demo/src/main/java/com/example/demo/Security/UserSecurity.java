//package com.example.demo.Security;
//
//import com.example.demo.Model.Admin;
//import com.example.demo.Model.Customer;
//import com.example.demo.Service.AdminService;
//import com.example.demo.Service.CustomerService;
//import org.springframework.stereotype.Component;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Component;
//
//@Component
//public class UserSecurity implements UserDetailsService{
//
//    private CustomerService customerService;
//    private AdminService adminService;
//
//    @Autowired
//    public UserSecurity(CustomerService customerService,AdminService adminService){
//        this.customerService = customerService;
//        this.adminService = adminService;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//
//        Customer customer = customerService.findByUsername(username);
//        Admin admin = adminService.findByUsername(username);
//
//        if(customer == null) return null;
//        else if(admin == null) return null;
//        else if(customer != null) return org.springframework.security.core.userdetails.User.builder()
//        .username(customer.getUsername())
//        .password(customer.getPassword())
//        .roles("Customer")
//        .build();
//        else return org.springframework.security.core.userdetails.User.builder()
//                    .username(admin.getUsername())
//                    .password(admin.getPassword())
//                    .roles("Admin")
//                    .build();
//    }
//}
