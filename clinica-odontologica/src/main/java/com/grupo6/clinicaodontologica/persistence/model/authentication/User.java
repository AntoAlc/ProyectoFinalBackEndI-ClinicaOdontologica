package com.grupo6.clinicaodontologica.persistence.model.authentication;


import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;

@Table(name = "Users")
@Entity
@Getter
@Setter
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_user")
    @SequenceGenerator(name = "user_seq", sequenceName = "BD_SEQUENCE_USER", allocationSize = 1)
    @Column
    private long id;

    @Column
    private String name;

    @Column
    private String username;

    @Column
    private String email;

    @Column
    private String password;


    @Enumerated(EnumType.STRING)
    private UserRoles userRoles;

    public User() {
    }

    public User(String name, String username, String email, String password, UserRoles userRoles) {

        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.userRoles = userRoles;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(userRoles.name()));
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", userName='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", UserRole=" + userRoles +
                '}';
    }

}
