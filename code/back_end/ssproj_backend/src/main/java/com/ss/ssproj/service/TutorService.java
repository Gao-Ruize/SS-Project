package com.ss.ssproj.service;

import com.ss.ssproj.entity.Tutor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TutorService {
    List<Tutor> findAll();
    void saveOrUpdate(Tutor tutor);
    Tutor findDistinctByUid(String uid);
}
