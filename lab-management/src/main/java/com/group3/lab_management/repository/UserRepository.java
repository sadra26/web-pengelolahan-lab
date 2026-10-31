package com.group3.lab_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.group3.lab_management.model.User;

public interface UserRepository extends JpaRepository<User,Integer>{

    User findByUsername(String username);

}