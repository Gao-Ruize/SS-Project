package com.example.demo.entity;

import lombok.Data;

@Data
public class User {

    private String name;
    private String password;
    private String role;

    public User() {
        name = "none";
        password = "none";
        role = "none";
    }

    public User(String name, String password, String role) {
        this.name = name;
        this.password = password;
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}
