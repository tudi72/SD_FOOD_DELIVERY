package com.example.demo.Repository;

import com.example.demo.Model.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority,Long> {

    Authority findAuthorityByRoleCode(String roleCole);
}
