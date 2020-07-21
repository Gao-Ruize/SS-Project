package com.ss.ssproj.service;

import com.ss.ssproj.entity.Student;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StudentService {
    Student saveOrUpdate(Student student);
    Student findDistinctByUid(String uid);
    Student findDistinctByStudentId(String studentId);
    List<Student> findAll();
}
