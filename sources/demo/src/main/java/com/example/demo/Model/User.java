package com.example.demo.Model;


import javax.persistence.*;

import lombok.*;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Entity
@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@ToString
@Builder

public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NonNull
    private int id;
    @NonNull private  String name;
    @NonNull private  String email;
    @NonNull private  String password;
    @Column private Date createdAt;
    @Column private Date updatedAt;
    @Column private boolean enabled;

    @NonNull
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name="user_auth",
            joinColumns = @JoinColumn(referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(referencedColumnName = "id"))
    private  Collection<Authority> authorities = new ArrayList<>();

    @Override public String getUsername() {
        return email;
    }
    @Override public boolean isAccountNonExpired() {
        return enabled;
    }
    @Override public boolean isAccountNonLocked() {
        return enabled;
    }
    @Override public boolean isCredentialsNonExpired() {
        return enabled;
    }


}
