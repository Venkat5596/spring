package com.example.spring_boot.jwt;

import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.*;

import static com.example.spring_boot.jwt.Permission.*;

public enum Role {
    USER(Collections.emptySet()),
    ADMIN(
            Set.of(
                    ADMIN_READ,
                    ADMIN_CREATE,
                    ADMIN_UPDATE,
                    ADMIN_DELETE,
                    MANAGER_CREATE,
                    MANAGER_UPDATE,
                    MANAGER_READ,
                    MANAGER_DELETE
            )
    ),
    MANAGER(
            Set.of(
                    MANAGER_CREATE,
                    MANAGER_UPDATE,
                    MANAGER_READ,
                    MANAGER_DELETE
            )
    );

    @Getter
    private final Set<Permission> permissions;

    Role(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public List<SimpleGrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        getPermissions().forEach(permission ->
                authorities.add(new SimpleGrantedAuthority(permission.getPermission()))
        );
        return Collections.unmodifiableList(authorities);
    }


}