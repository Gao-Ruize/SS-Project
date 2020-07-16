package com.ss.ssproj.serviceimpl;

import com.ss.ssproj.dao.JwcMessageDao;
import com.ss.ssproj.dao.ReadJwcMsgDao;
import com.ss.ssproj.entity.JwcMessage;
import com.ss.ssproj.entity.ReadJwcMsg;
import com.ss.ssproj.service.ReadJwcMsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReadJwcMsgServiceImpl implements ReadJwcMsgService {
    @Autowired
    ReadJwcMsgDao readJwcMsgDao;

    @Override
    public ReadJwcMsg findDistinctByStudentidAndMsgid(String studentid, int msgid) {
        return readJwcMsgDao.findDistinctByStudentidAndMsgid(studentid, msgid);
    }

    @Override
    public ReadJwcMsg findDistinctByTutoridAndMsgid(String tutorid, int msgid) {
        return readJwcMsgDao.findDistinctByTutoridAndMsgid(tutorid, msgid);
    }

    @Override
    public ReadJwcMsg saveOrUpdate(ReadJwcMsg readJwcMsg) {
        return readJwcMsgDao.save(readJwcMsg);
    }

    @Override
    public List<ReadJwcMsg> findAllByStudentidAndIfread(String studentid, int ifread) {
        return readJwcMsgDao.findAllByStudentidAndIfread(studentid, ifread);
    }
}
