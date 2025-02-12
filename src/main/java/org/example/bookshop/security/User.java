package org.example.bookshop.security;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true)
    private String username;
    private String password;
    private String email;
    private String phoneNumber;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles=
            new HashSet<>();
    public void addRole(Role role){
        this.roles.add(role);
    }
}
