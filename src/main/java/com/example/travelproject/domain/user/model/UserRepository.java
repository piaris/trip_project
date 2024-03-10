package com.example.travelproject.domain.user.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface  UserRepository extends JpaRepository<UserEntity,String>{
     @Query(value = "select * from user where name = :name", nativeQuery = true)
    public UserEntity getUserDtoByName(@Param(value = "name") String name);
}
