package com.ss.ssproj.repository;

import com.ss.ssproj.entity.Tutor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TutorRepository extends JpaRepository<Tutor, Integer> {
    List<Tutor> findAll();
    Tutor save(Tutor tutor);
    Tutor findDistinctByUid(String uid);
    Tutor findDistinctByTutorid(String tutorId);
}
