package com.ss.ssproj.daoimpl;

import com.ss.ssproj.dao.ReadInsMsgDao;
import com.ss.ssproj.entity.ReadInsMsg;
import com.ss.ssproj.repository.ReadInsMsgRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReadInsMsgImpl implements ReadInsMsgDao {
    @Autowired
    ReadInsMsgRepository readInsMsgRepository;

    @Override
    public ReadInsMsg findDistinctByStudentidAndMsgid(String studentid, int msgid) {
        return readInsMsgRepository.findDistinctByStudentidAndMsgid(studentid, msgid);
    }

    @Override
    public ReadInsMsg save(ReadInsMsg readInsMsg) {
        return readInsMsgRepository.save(readInsMsg);
    }

    @Override
    public List<ReadInsMsg> findAllByStudentid(String studentid) {
        return readInsMsgRepository.findAllByStudentid(studentid);
    }

    @Override
    public List<ReadInsMsg> findAllByMsgid(int msgid) {
        return readInsMsgRepository.findAllByMsgid(msgid);
    }
}
