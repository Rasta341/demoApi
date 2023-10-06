package com.example.demoapi.userentities;

import jakarta.persistence.*;

@Entity
@Table(name = "USERS")
public class User {
    @SequenceGenerator(
            name = "SequenceGenerator",
            allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "SequenceGenerator")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "email")
    private String email;
    @Column(name = "phone")
    private String phone;

    public User(){}

    public User(String name, String email, String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    @Override
    public String toString() {
        return "User [id: " + id + " , name: " + name +
                " , email: " + email + " , phone: " + phone;

    }
}
