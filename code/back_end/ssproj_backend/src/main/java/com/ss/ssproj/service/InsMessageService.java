package com.ss.ssproj.service;

import com.ss.ssproj.entity.InsMessage;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface InsMessageService {
    InsMessage findDistinctById(int id);
    InsMessage saveOrUpdate(InsMessage insMessage);
    List<InsMessage> findAllByTutorid(String tutorid);
}
