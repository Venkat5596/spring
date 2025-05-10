package com.example.database_jpa.dto.mapper;


public interface Mapper<A,B>{

    B mapToDto(A a);
    A mapToEntity(B b);
}
