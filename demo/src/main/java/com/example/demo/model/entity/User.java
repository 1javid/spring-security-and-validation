package com.example.demo.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "Username can't be empty")
    private String username;
    @NotEmpty(message = "Username can't be empty")
    private String password;
    @Email(regexp = "^(.+)@(.+)\\.(.+)$", message = "Email should follow a standard abc@def.xyz")
    private String email;
    private String roles; // ROLE_USER, ROLE_ADMIN

    @Transient
    private List<Role> rolesList = new ArrayList<>(Arrays.asList(new Role("ROLE_USER"), new Role("ROLE_ADMIN")));
    @Transient
    private List<String> authorities =  new ArrayList<>(Arrays.asList("ROLE_USER"));

    public List<GrantedAuthority> getAuthorities() {
        return authorities.stream().map(role -> new SimpleGrantedAuthority(role)).collect(Collectors.toList());
    }

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public User addRole(String authority) {
        this.authorities.add(authority);
        return this;
    }

    @PrePersist
    @PreUpdate
    private void saveRoles() {this.roles = String.join(";", this.authorities);}

    @PostLoad
    private void readRoles() {this.authorities = Arrays.stream(this.roles.split(";")).collect(Collectors.toList());}

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}