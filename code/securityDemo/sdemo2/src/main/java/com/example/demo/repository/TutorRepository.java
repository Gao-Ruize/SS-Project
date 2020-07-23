package com.example.demo.repository;

import com.example.demo.entity.Tutor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TutorRepository extends JpaRepository<Tutor, Integer> {
    Tutor findDistinctByTutorname(String tutorname);
    Tutor findById(int id);
}
