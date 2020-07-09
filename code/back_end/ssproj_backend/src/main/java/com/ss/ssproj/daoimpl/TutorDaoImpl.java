package com.ss.ssproj.daoimpl;

import com.ss.ssproj.dao.TutorDao;
import com.ss.ssproj.entity.Tutor;
import com.ss.ssproj.repository.TutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TutorDaoImpl implements TutorDao {
    @Autowired
    TutorRepository tutorRepository;

    @Override
    public List<Tutor> findAll() {
        return tutorRepository.findAll();
    };

    @Override
    public void saveOrUpdate(Tutor tutor) {
        tutorRepository.save(tutor);
    }

}
