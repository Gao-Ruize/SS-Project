package com.example.demo.dao;

import com.example.demo.entity.Tutor;
import com.example.demo.repository.TutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TutorDao {
    @Autowired
    TutorRepository tutorRepository;

    public Tutor findDistinctByTutorname(String tutorname) {
        return tutorRepository.findDistinctByTutorname(tutorname);
    }

    public Tutor findById(int id) {
        return tutorRepository.findById(id);
    }
}
