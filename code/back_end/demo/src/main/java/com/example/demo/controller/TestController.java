package com.example.demo.controller;

import com.auth0.jwt.interfaces.Claim;
import com.example.demo.entity.User;
import com.example.demo.utils.JWTUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/hello")
public class TestController {

    @GetMapping("detail")
    public String hello() {
        return "hello world!";
    }

}
