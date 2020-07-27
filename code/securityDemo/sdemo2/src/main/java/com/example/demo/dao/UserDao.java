package com.example.demo.dao;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {
    @Autowired
    UserRepository userRepository;

    public User findByUserName(User user) {
        return userRepository.findDistinctByUsername(user.getUsername());
    }

    public User findById(User user) {
        return userRepository.findById(user.getId());
    }
}
