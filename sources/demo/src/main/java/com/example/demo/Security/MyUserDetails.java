package com.example.demo.Security;

import com.example.demo.Model.User;
import com.example.demo.Repository.UserRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class MyUserDetails implements UserDetailsService {

    private final UserRepository userRepository;
    private final Logger log = Logger.getLogger(MyUserDetails.class);

    @Autowired
    public  MyUserDetails(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if(user == null){
            log.error("UserService : loadUserByUsername not found in database");
            throw new UsernameNotFoundException("User not found in database");
        }
        else {
            log.info("UserService : loadUserByUsername found");
        }

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                user.getAuthorities());
    }
}
