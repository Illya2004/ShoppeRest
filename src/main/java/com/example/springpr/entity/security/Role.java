package com.example.springpr.entity.security;

import com.example.springpr.enums.RolesEnum;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "roles")
@Data
public class Role {
    @Id
    @GeneratedValue(generator = "roles_sequence")
    @SequenceGenerator(name="roles_sequence", sequenceName="roles_sequence", allocationSize=1)
    private Long id;

    private String name;

}
