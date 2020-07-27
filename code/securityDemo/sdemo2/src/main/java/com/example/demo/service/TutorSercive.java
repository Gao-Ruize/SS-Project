package com.example.demo.service;

import com.example.demo.dao.TutorDao;
import com.example.demo.entity.Tutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TutorSercive {
    @Autowired
    TutorDao tutorDao;

    public Tutor findDistinctByTutorname(String tutorname) {
        return tutorDao.findDistinctByTutorname(tutorname);
    }

    public Tutor findById(int id) {
        return tutorDao.findById(id);
    }
}
