package com.ss.ssproj.service;

import com.ss.ssproj.entity.JwcMessage;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface JwcMessageService {
    JwcMessage findById(int id);
    List<JwcMessage> findAll();

}
