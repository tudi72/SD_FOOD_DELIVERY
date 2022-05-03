package com.example.demo.Controller;

import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.security.spec.InvalidKeySpecException;

import com.example.demo.Model.DTOs.AuthenticationDTO;
import com.example.demo.Model.DTOs.LoginResponseDTO;
import com.example.demo.Model.DTOs.UserDTO;
import com.example.demo.Model.User;
import com.example.demo.Security.JWT.JWTTokenHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/auth")
@CrossOrigin
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    JWTTokenHelper jWTTokenHelper;

    @Autowired
    private UserDetailsService userDetailsService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthenticationDTO authenticationDTO) throws InvalidKeySpecException, NoSuchAlgorithmException {

        final Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                authenticationDTO.getEmail(), authenticationDTO.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        User user=(User)authentication.getPrincipal();
        String jwtToken=jWTTokenHelper.generateToken(user.getUsername());

        LoginResponseDTO responseDTO = new LoginResponseDTO(jwtToken);

        return ResponseEntity.ok(responseDTO);
    }


    @GetMapping("//userinfo")
    public ResponseEntity<?> getUserInfo(Principal user){
        User userObj = (User) userDetailsService.loadUserByUsername(user.getName());

        UserDTO userInfo = new UserDTO(userObj.getName(),userObj.getEmail(),userObj.getAuthorities().toArray());


        return ResponseEntity.ok(userInfo);



    }
}