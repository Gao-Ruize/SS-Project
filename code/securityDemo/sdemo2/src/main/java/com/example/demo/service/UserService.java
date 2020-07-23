package com.example.demo.service;

import com.example.demo.dao.UserDao;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserDao userDao;

    public User findByUserName(User user) {
        return userDao.findByUserName(user);
    }

    public User findById(User user) {
        return userDao.findById(user);
    }
}
