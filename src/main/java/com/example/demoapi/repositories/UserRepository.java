package com.example.demoapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demoapi.entities.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    List<User> findUserByEmail(String email);
    List<User> findByNameContaining(String name);

    boolean existsByEmailAndPhone(String email, String phone);
}
