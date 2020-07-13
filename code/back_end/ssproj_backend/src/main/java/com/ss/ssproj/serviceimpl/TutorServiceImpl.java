package com.ss.ssproj.serviceimpl;

import com.ss.ssproj.dao.TutorDao;
import com.ss.ssproj.entity.Tutor;
import com.ss.ssproj.service.TutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TutorServiceImpl implements TutorService{
    @Autowired
    TutorDao tutorDao;

    @Override
    public List<Tutor> findAll(){
        return tutorDao.findAll();
    }

    @Override
    public void saveOrUpdate(Tutor tutor) {
        tutorDao.saveOrUpdate(tutor);
    }

    @Override
    public Tutor findDistinctByUid(String uid) {
        return tutorDao.findDistinctByUid(uid);
    }
}
