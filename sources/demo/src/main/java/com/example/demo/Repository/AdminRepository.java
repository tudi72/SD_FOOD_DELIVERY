package com.example.demo.Repository;

import com.example.demo.Model.Admin;
import com.example.demo.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin,Integer> {

    Admin findAdminByUser(User user);
}
