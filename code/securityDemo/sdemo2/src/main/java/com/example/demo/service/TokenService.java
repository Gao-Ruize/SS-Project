package com.example.demo.service;

import com.example.demo.entity.Tutor;
import com.example.demo.entity.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class TokenService {

    public String getToken(User user) {
        Date generateDate = new Date();
        Calendar nowTime = Calendar.getInstance();
        nowTime.add(Calendar.MINUTE, 30);
        Date expiresDate = nowTime.getTime();
        String token="";
        token= JWT.create().withAudience(user.getUsername())// 将 user id 保存到 token 里面
                .withExpiresAt(expiresDate)
                .withIssuedAt(generateDate)
                .sign(Algorithm.HMAC256(user.getPassword()));// 以 password 作为 token 的密钥
        return token;
    }

    public String getTutorToken(Tutor tutor) {
        Date generateDate = new Date();
        Calendar nowTime = Calendar.getInstance();
        nowTime.add(Calendar.MINUTE, 30);
        Date expiresDate = nowTime.getTime();
        String token = "";
        token = JWT.create().withAudience(tutor.getTutorname())
                .withExpiresAt(expiresDate)
                .withIssuedAt(generateDate)
                .sign(Algorithm.HMAC256(tutor.getPassword()));
        return token;
    }
}
