package com.example.springboojpacrud.repository;

import com.example.springboojpacrud.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
    User findByUsername(String username);


}
