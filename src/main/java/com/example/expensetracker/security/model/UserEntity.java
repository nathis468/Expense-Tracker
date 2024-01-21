package com.example.expensetracker.security.model;

import java.util.Collection;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.expensetracker.model.Role;

import lombok.Data;

@Data
public class UserEntity implements UserDetails{
    @Id
    private String id;

    @Field("user_name")
    private String userName;
    private String email;
    private String password;
    @Field(targetType = FieldType.STRING)
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_"+role.name()));
    }
    @Override
    public String getUsername() {
        return userName;
    }
    @Override
    public String getPassword() {
       return password;
    }
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
