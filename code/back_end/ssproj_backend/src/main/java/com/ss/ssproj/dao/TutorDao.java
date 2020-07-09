package com.ss.ssproj.dao;

import com.ss.ssproj.entity.Tutor;

import java.util.List;

public interface TutorDao {
    public List<Tutor> findAll();
    public void saveOrUpdate(Tutor tutor);
}
