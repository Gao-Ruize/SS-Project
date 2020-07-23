package com.ss.ssproj.service;

import org.springframework.stereotype.Service;

@Service
public interface TokenService {
    String getToken(String rid, String uid);
}
