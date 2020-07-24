package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.utils.JWTUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/test")
    @CrossOrigin
    public String test() {
        User user = new User("a", "b", "c");
        String token = JWTUtils.createToken("yyh", "xyx");

        return "Imsb";
    }

}
