package com.example.demo.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.bson.internal.Base64;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.*;

public class JWTUtils {

    private static final String JWT_SECRET = "test_of_yyh";

    /**
     * 签发 JWT
     *
     * @param userId
     * @param subject   可以是 JSON 数据，尽可能少
     * @param ttlMillis
     * @return
     */
    public static String createJWT(String userId, String subject, long ttlMillis) {
        SignatureAlgorithm algorithm = SignatureAlgorithm.HS256;
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        // header Map
        Map<String, Object> map = new HashMap<>();
        map.put("alg", "HS256");
        map.put("typ", "JWT");

        SecretKey secretKey = generalKey();

        Claims claims = Jwts.claims();
        claims.setId(UUID.randomUUID().toString()) // jti (JWT ID)，防止jwt被重新发送
                .setSubject(subject) // 主题
                .setIssuedAt(now) // 签发时间
                .setIssuer("user"); // 签发者

        // payload 中 放入自定义信息
        Map<String, Object> selfMap = new HashMap<>();
        selfMap.put("userId", userId);
        claims.putAll(selfMap);

        JwtBuilder builder = Jwts.builder().setHeader(map) // header
                .setClaims(claims) // 使用 JSON 实例设置 payload
                .signWith(algorithm, secretKey); // 签名算法以及密钥
        if (ttlMillis >= 0) {
            long expMillis = nowMillis + ttlMillis;
            Date expDate = new Date(expMillis);
            builder.setExpiration(expDate); // 过期时间
        }
        return builder.compact();
    }

    private static SecretKey generalKey() {
        byte[] encodedKey = Base64.decode(JWT_SECRET);
        SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
        return key;
    }

    /**
     *
     * 解析JWT字符串
     *
     * @param jwt
     * @return
     * @throws Exception
     */
    public static Claims parseJWT(String jwt) {
        Claims claims = null;
        try {
            SecretKey secretKey = generalKey();
            claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwt).getBody();
        } catch (Exception e) {
            System.err.println("token 校验失败");
        }
        return claims;
    }


}
