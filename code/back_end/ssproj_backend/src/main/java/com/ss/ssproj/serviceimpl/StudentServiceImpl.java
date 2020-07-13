package com.ss.ssproj.serviceimpl;

import com.ss.ssproj.dao.StudentDao;
import com.ss.ssproj.entity.Student;
import com.ss.ssproj.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    StudentDao studentDao;

    @Override
    public void saveOrUpdate(Student student) {
        studentDao.saveOrUpdate(student);
    }

    @Override
    public Student findDistinctByUid(String uid) {
        return studentDao.findDistinctByUid(uid);
    }
}
