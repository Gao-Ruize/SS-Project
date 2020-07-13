package com.ss.ssproj.dao;

import com.ss.ssproj.entity.Student;

import java.util.List;

public interface StudentDao {
    void saveOrUpdate(Student student);
    Student findDistinctByUid(String uid);
}
