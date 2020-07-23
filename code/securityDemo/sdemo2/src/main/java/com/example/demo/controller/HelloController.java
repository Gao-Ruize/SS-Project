package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.annotation.PassToken;
import com.example.demo.annotation.TutorLoginToken;
import com.example.demo.annotation.UserLoginToken;
import com.example.demo.entity.Tutor;
import com.example.demo.entity.User;
import com.example.demo.service.TokenService;
import com.example.demo.service.TutorSercive;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class HelloController {
    @Autowired
    UserService userService;

    @Autowired
    TutorSercive tutorSercive;

    @Autowired
    TokenService tokenService;

    @UserLoginToken
    @ResponseBody
    @GetMapping(value = "user/hello")
    public String hello() {
        return "Hello !";
    }

    @TutorLoginToken
    @ResponseBody
    @GetMapping(value = "tutor/hello")
    public String THello() {
        return "tutor hello!";
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
    @PostMapping(value = "user/login")
    public String login(@RequestBody User user) {
        //假设用ada 123456登陆
        JSONObject jsonObject = new JSONObject();
        String username = user.getUsername();
        User tmp1 = userService.findByUserName(user);
        if(tmp1 == null) {
            Tutor tmp2 = tutorSercive.findDistinctByTutorname(username);
            if(tmp2 == null) {
                jsonObject.put("token","xx");
                jsonObject.put("user", null);
                return "xx";

            }
            String token = tokenService.getTutorToken(tmp2);
            jsonObject.put("token", token);
            jsonObject.put("user", tmp2);
            return token;

        }
        String toKen = tokenService.getToken(user);
        jsonObject.put("token", toKen);
        jsonObject.put("user", user);
        return toKen;
    }

}
