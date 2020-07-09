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

    public List<Instruct> findAll() {
        return instructRepository.findAll();
    }
}
