package com.example.demoapi.controller;

import com.example.demoapi.userentities.User;
import com.example.demoapi.userrepositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8082")
    @RestController
    @RequestMapping("/api")
    public class UserController {
        @Autowired
        private UserRepository userRepository;

        @GetMapping("/users")
        public ResponseEntity<List<User>> getAllUsers(@RequestParam(required = false) String name) {
            try {
                List<User> users = new ArrayList<User>();
                if (name == null)
                    users.addAll(userRepository.findAll());
                else
                    users.addAll(userRepository.findByNameContaining(name));
                if (users.isEmpty()) {
                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                }
                return new ResponseEntity<>(users, HttpStatus.OK);
            } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        @GetMapping("/users/{id}")
        public ResponseEntity<User> getUserById(@PathVariable("id") Long id) {
            Optional<User> userData = userRepository.findById(id);

            if (userData.isPresent()) {
                return new ResponseEntity<>(userData.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        try {
            // Проверяем наличие пользователя с такими же полями
            boolean userExists = userRepository.existsByEmailAndPhone(user.getEmail(), user.getPhone());
            if (userExists) {
                return new ResponseEntity<>(null, HttpStatus.CONFLICT); // Возвращаем статус конфликта, если пользователь уже существует
            }

            User _user = userRepository.save(new User(user.getName(), user.getEmail(), user.getPhone()));
            return new ResponseEntity<>(_user, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

        @PutMapping("/users/{id}")
        public ResponseEntity<User> updateUser(@PathVariable("id") Long id,
                                               @RequestBody User user) {
            Optional<User> userData = userRepository.findById(id);

            if (userData.isPresent()) {
                User _user = userData.get();
                if (user.getName() != null) {
                _user.setName(user.getName());
                }
                if(user.getEmail() != null) {
                _user.setEmail(user.getEmail());
                }
                if(user.getPhone() != null) {
                _user.setPhone(user.getPhone());
                }
                return  new ResponseEntity<>(userRepository.save(_user), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }

        @DeleteMapping("/users/{id}")
        public ResponseEntity<HttpStatus> deleteUser(@PathVariable(value = "id") Long id) {
            try {
                userRepository.deleteById(id);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        @DeleteMapping("/users")
        public ResponseEntity<HttpStatus> deleteAllUsers() {
            try {
                userRepository.deleteAll();
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

