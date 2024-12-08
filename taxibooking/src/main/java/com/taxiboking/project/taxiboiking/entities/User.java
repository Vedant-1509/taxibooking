package com.taxiboking.project.taxiboiking.entities;

import com.taxiboking.project.taxiboiking.entities.enums.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "app_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true)
    private String email;

    private String password;


    @ElementCollection(fetch = FetchType.LAZY)
    @Enumerated(EnumType.STRING)//in this we store the role in complete string instead in the form (0 for Admin 1 for User and 2 fro Driver(for the use type ORDINAL) )
    private Set<Role> roles;

}
