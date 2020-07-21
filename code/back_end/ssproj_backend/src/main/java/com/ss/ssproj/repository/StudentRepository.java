package com.ss.ssproj.repository;

import com.ss.ssproj.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Integer> {
    Student save(Student student);
    Student findDistinctByUid(String uid);
    Student findDistinctByStudentid(String studentId);
    List<Student> findAll();
}
