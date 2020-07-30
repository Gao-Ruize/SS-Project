package com.ss.ssproj.serviceimpl;

import com.ss.ssproj.dao.StudentDao;
import com.ss.ssproj.entity.Student;
import com.ss.ssproj.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    StudentDao studentDao;

    @Override
    public Student saveOrUpdate(Student student) {
        return studentDao.saveOrUpdate(student);
    }

    @Override
    public Student findDistinctByUid(String uid) {
        return studentDao.findDistinctByUid(uid);
    }

    @Cacheable("students")
    @Override
    public Student findDistinctByStudentId(String studentId) {
        return studentDao.findDistinctByStudentId(studentId);
    }

    @Override
    public List<Student> findAll() {return studentDao.findAll();}
}
