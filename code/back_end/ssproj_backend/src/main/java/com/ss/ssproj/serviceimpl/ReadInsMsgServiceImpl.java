package com.ss.ssproj.serviceimpl;

import com.ss.ssproj.dao.ReadInsMsgDao;
import com.ss.ssproj.entity.ReadInsMsg;
import com.ss.ssproj.service.ReadInsMsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReadInsMsgServiceImpl implements ReadInsMsgService {
    @Autowired
    ReadInsMsgDao readInsMsgDao;

    @Override
    public ReadInsMsg findDistinctByStudentidAndMsgid(String studentid, int msgid) {
        return readInsMsgDao.findDistinctByStudentidAndMsgid(studentid, msgid);
    }

    @Override
    public ReadInsMsg save(ReadInsMsg readInsMsg) {
        return readInsMsgDao.save(readInsMsg);
    }

    @Override
    public List<ReadInsMsg> findAllByStudentid(String studentid) {
        return readInsMsgDao.findAllByStudentid(studentid);
    }

    @Override
    public List<ReadInsMsg> findAllByMsgid(int msgid) {
        return readInsMsgDao.findAllByMsgid(msgid);
    }
}
