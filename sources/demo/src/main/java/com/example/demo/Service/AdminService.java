package com.example.demo.Service;

import com.example.demo.Model.Admin;
import com.example.demo.Repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminService {

    private AdminRepository adminRepository;
//
//    public Admin findByUsername(String username) {
//        return adminRepository.findByUsername(username);
//    }
}
