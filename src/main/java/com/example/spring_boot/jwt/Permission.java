package com.example.spring_boot.jwt;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {
    ADMIN_READ("admin:read"),
    ADMIN_UPDATE("admin:update"),
    ADMIN_DELETE( "admin:delete"),
    ADMIN_CREATE("admin:create"),


    MANAGER_READ("manager:read"),
    MANAGER_UPDATE( "manager:write"),
    MANAGER_DELETE( "manager:delete"),
    MANAGER_CREATE( "manager:create");


    @Getter
    private final String permission;
}
