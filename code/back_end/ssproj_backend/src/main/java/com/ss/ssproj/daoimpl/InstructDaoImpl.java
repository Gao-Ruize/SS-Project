package com.ss.ssproj.daoimpl;

import com.ss.ssproj.dao.InstructDao;
import com.ss.ssproj.entity.Instruct;
import com.ss.ssproj.repository.InstructRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class InstructDaoImpl implements InstructDao {
    @Autowired
    InstructRepository instructRepository;

    @Override
    public List<Instruct> findAll() {
        return instructRepository.findAll();
    }

    @Override
    public Instruct save(Instruct instruct) {
        return instructRepository.save(instruct);
    }

    @Override
    public List<Instruct> findAllByTutorid(String tutorid) {
        return instructRepository.findAllByTutorid(tutorid);
    }
}
