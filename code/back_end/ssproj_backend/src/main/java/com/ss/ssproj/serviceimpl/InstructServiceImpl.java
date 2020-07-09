package com.ss.ssproj.serviceimpl;

import com.ss.ssproj.dao.InstructDao;
import com.ss.ssproj.entity.Instruct;
import com.ss.ssproj.service.InsMessageService;
import com.ss.ssproj.service.InstructService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstructServiceImpl implements InstructService {
    @Autowired
    InstructDao instructDao;

    public List<Instruct> findAll() {
        return instructDao.findAll();
    }
}
