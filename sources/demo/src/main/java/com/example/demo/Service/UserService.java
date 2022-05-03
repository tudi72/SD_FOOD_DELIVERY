package com.example.demo.Service;

import com.example.demo.Model.Authority;
import com.example.demo.Model.User;
import com.example.demo.Repository.AdminRepository;
import com.example.demo.Repository.AuthorityRepository;
import com.example.demo.Repository.CustomerRepository;
import com.example.demo.Repository.UserRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService{

    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;
    private final Logger log = Logger.getLogger(UserService.class);


    @Autowired
    public UserService(AuthorityRepository authorityRepository,
                       UserRepository repository){
        this.userRepository = repository;
        this.authorityRepository = authorityRepository;
    }

    public User saveUser(User user) {
        log.info("UserService : saveUser to database");
        return userRepository.save(user);
    }

    public Authority saveAuthority(Authority authority){
        return authorityRepository.save(authority);
    }

    public User addRoleToUser(String username,String roleCode){
        User user = userRepository.findByEmail(username);
        user.getAuthorities().add(authorityRepository.findAuthorityByRoleCode(roleCode));
        return userRepository.save(user);
    }

    public User getUser(String email) {
        return userRepository.findByEmail(email);
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }
}
