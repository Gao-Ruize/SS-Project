package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.annotation.PassToken;
import com.example.demo.annotation.UserLoginToken;
import com.example.demo.entity.User;
import com.example.demo.service.TokenService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @Autowired
    UserService userService;

    @Autowired
    TokenService tokenService;

    @UserLoginToken
    @ResponseBody
    @GetMapping(value = "user/hello")
    public String hello() {
        return "Hello !";
    }

    @ResponseBody
    @GetMapping(value = "user/info")
    public User getInfo() {
        User user = new User();
        user.setId(1);
        user.setUsername("bob");
        return userService.findById(user);
    }

    @PassToken
    @ResponseBody
    @GetMapping(value = "user/getToken")
    public String getToken() {
        User user = new User();
        user.setId(2);
        user.setPassword("123456");
        user.setUsername("ada");
        return tokenService.getToken(user);
    }

    @ResponseBody
    @GetMapping(value = "user/login")
    public Object login() {
        //假设用ada 123456登陆
        User userForm = new User();
        userForm.setUsername("ada");
        userForm.setPassword("123456");
        userForm.setId(1);
        JSONObject jsonObject = new JSONObject();
        String toKen = tokenService.getToken(userForm);
        jsonObject.put("token", toKen);
        jsonObject.put("user", userForm);
        return jsonObject;
    }

}
