package com.ss.ssproj.daoimpl;

import com.ss.ssproj.dao.StudentDao;
import com.ss.ssproj.entity.Student;
import com.ss.ssproj.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentDaoImpl implements StudentDao {
    @Autowired
    StudentRepository studentRepository;

    @Override
    public Student saveOrUpdate(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student findDistinctByUid(String uid) {
        return studentRepository.findDistinctByUid(uid);
    }

    @Override
    public Student findDistinctByStudentId(String studentId) {
        return studentRepository.findDistinctByStudentid(studentId);
    }

    @Override
    public List<Student> findAll() {
        return studentRepository.findAll();
    }
}
