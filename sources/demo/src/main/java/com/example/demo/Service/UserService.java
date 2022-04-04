package com.example.demo.Service;

import com.example.demo.Model.User;
import com.example.demo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository repository;

    @Autowired
    public UserService(UserRepository repository){
        this.repository = repository;
    }

    public User addUser(User user) {
        User destination = repository.saveAndFlush(user);
        return destination;
    }

    public List<User> getAllUsers() {
        return repository.findAll();
    }


}
