package com.ss.ssproj.service;

import com.ss.ssproj.entity.JwcMessage;
import org.springframework.stereotype.Service;

@Service
public interface JwcMessageService {
    JwcMessage findById(int id);
}
