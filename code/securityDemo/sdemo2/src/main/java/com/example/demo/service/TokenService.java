package com.example.demo.service;

import com.example.demo.entity.Tutor;
import com.example.demo.entity.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

    public String getToken(User user) {
        String token="";
        token= JWT.create().withAudience(user.getUsername())// 将 user id 保存到 token 里面
                .sign(Algorithm.HMAC256(user.getPassword()));// 以 password 作为 token 的密钥
        return token;
    }

    public String getTutorToken(Tutor tutor) {
        String token = "";
        token = JWT.create().withAudience(tutor.getTutorname())
                .sign(Algorithm.HMAC256(tutor.getPassword()));
        return token;
    }
}
