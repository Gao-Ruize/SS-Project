package com.ss.ssproj.service;

import com.ss.ssproj.entity.Student;
import org.springframework.stereotype.Service;

@Service
public interface StudentService {
    void saveOrUpdate(Student student);
    Student findDistinctByUid(String uid);
    Student findDistinctByStudentId(String studentId);
}
