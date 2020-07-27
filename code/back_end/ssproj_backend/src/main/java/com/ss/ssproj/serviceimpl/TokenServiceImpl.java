package com.ss.ssproj.serviceimpl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.ss.ssproj.service.TokenService;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class TokenServiceImpl implements TokenService {
    @Override
    public String getToken(String rid, String uid) {
        //设置签发与过期时间
        Date generateDate = new Date();
        Calendar nowTime = Calendar.getInstance();
        nowTime.add(Calendar.MINUTE, 30);
        Date expiresDate = nowTime.getTime();
        String token="";
        token= JWT.create().withAudience(rid) // 将 rid 存入token
                .withExpiresAt(expiresDate)
                .withIssuedAt(generateDate)
                .sign(Algorithm.HMAC256(uid));// 以 uid 作为 token 的密钥
        return token;
    }
}
