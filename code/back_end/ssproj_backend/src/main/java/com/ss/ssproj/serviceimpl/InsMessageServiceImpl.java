package com.ss.ssproj.serviceimpl;

import com.ss.ssproj.dao.InsMessageDao;
import com.ss.ssproj.entity.InsMessage;
import com.ss.ssproj.service.InsMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InsMessageServiceImpl implements InsMessageService {
    @Autowired
    InsMessageDao insMessageDao;

    @Override
    public InsMessage findDistinctById(int id) {
        return insMessageDao.findDistinctById(id);
    }

    @Override
    public InsMessage saveOrUpdate(InsMessage insMessage) {
        return insMessageDao.save(insMessage);
    }

    @Override
    public List<InsMessage> findAllByTutorid(String tutorid) {
        return insMessageDao.findAllByTutorid(tutorid);
    }

}
