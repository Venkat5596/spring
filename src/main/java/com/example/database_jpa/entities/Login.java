package com.example.database_jpa.entities;

import com.example.database_jpa.jwt.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.Collections;

/**
 * Entity class representing user authentication and authorization details.
 * Implements Spring Security's UserDetails interface for authentication.
 */
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")  // Better naming convention for database table
public class Login implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role;

    /**
     * Copy constructor for creating a new Login instance from existing one
     * @param login The existing Login instance to copy from
     * @throws IllegalArgumentException if login parameter is null
     */
    public Login(@NotNull Login login) {
        this.id = login.getId();
        this.username = login.getUsername();
        this.password = login.getPassword();
        this.role = login.getRole();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (role == null) {
            return Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")); // Default role
        }
        return Collections.singleton(new SimpleGrantedAuthority("ROLE_" + role.name()));
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