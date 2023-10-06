package com.example.demoapi.service;

import com.example.demoapi.userentities.User;
import com.example.demoapi.userrepositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Transactional
    public void userServiceRepository() {
        Optional<User> usersOptional = userRepository.findById(127L);
    }

}
