package com.example.springpr.repository.security;

import com.example.springpr.entity.security.Role;
import com.example.springpr.enums.RolesEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolesRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);

}
