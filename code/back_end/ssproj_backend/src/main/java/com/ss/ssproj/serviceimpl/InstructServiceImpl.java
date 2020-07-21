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

    @Override
    public List<Instruct> findAll() {
        return instructDao.findAll();
    }

    @Override
    public Instruct saveOrUpdate(Instruct instruct) {
        return instructDao.save(instruct);
    }

    @Override
    public List<Instruct> findAllByTutorid(String tutorid) {
        return instructDao.findAllByTutorid(tutorid);
    }

    @Override
    public Instruct findDistinctByStudentidAndTutorid(String studentid, String tutorid) {
        return instructDao.findDistinctByStudentidAndTutorid(studentid, tutorid);
    }

    @Override
    public Instruct findDistinctByStudentid(String studentid) {
        return instructDao.findDistinctByStudentid(studentid);
    }

    @Override
    public void deleteById(int id) {
        instructDao.deleteById(id);
    }
}
