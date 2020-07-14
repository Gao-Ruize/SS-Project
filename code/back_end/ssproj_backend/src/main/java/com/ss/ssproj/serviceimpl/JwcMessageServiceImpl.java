package com.ss.ssproj.serviceimpl;

import com.ss.ssproj.dao.JwcMessageDao;
import com.ss.ssproj.entity.JwcMessage;
import com.ss.ssproj.service.JwcMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JwcMessageServiceImpl implements JwcMessageService {
    @Autowired
    JwcMessageDao jwcMessageDao;

    @Override
    public JwcMessage findById(int id) {
        return jwcMessageDao.findByJwcId(id);
    }

    @Override
    public List<JwcMessage> findAll() {
        return jwcMessageDao.findAll();
    }
}
